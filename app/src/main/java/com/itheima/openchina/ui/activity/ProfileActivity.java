package com.itheima.openchina.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.itheima.openchina.R;
import com.itheima.openchina.appcontrol.Constant;
import com.itheima.openchina.appcontrol.NetDataApi;
import com.itheima.openchina.bases.BaseActivity;
import com.itheima.openchina.beans.UserInfo;
import com.itheima.openchina.utils.FileUtil;
import com.itheima.openchina.utils.ImageUtils;
import com.itheima.openchina.utils.SpUtil;
import com.itheima.openchina.utils.ToastUtil;
import com.itheima.openchina.utils.Utils;
import com.itheima.openchina.wedigt.HeadBottomMenu;
import com.itheima.openchina.wedigt.LoadDialog;
import com.jaeger.library.StatusBarUtil;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * User:DoctorHe <p/>
 * Date: 2017/11/6 <p/>
 * Time: 11:47 <p/>
 * Project:OpenChina <p/>
 * Package:com.itheima.openchina.ui.activity <p/>
 * Desc: 我的资料界面
 */

public class ProfileActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.pager_title)
    TextView mPagerTitle;
    @BindView(R.id.head)
    CircleImageView mHead;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_jionTime)
    TextView mTvJionTime;
    @BindView(R.id.tv_from)
    TextView mTvFrom;
    @BindView(R.id.tv_develop)
    TextView mTvDevelop;
    @BindView(R.id.tv_zhuanchang)
    TextView mTvZhuanchang;
    @BindView(R.id.btn_signout)
    Button mBtnSignout;
    private UserInfo mUserInfo;

    private HeadBottomMenu mHeadBottomMenu;

    /**
     * 照片地址
     */
    private Uri origUri;

    /**
     * 头像保存地址
     */
    private final static String FILE_SAVEPATH = Environment
            .getExternalStorageDirectory().getAbsolutePath()
            + "/OpenChina/Portrait/";
    /**
     * 头像网络地址
     */
    private String protraitPath;

    /**
     * 图片裁剪尺寸
     */
    private final static int CROP = 200;
    /**
     * 头像文件
     */
    private File protraitFile;

    /**
     * 加载对话框
     */
    private LoadDialog mLoadDialog;

    /**
     * 要上传的头像位图对象
     */
    private Bitmap protraitBitmap;

    @Override
    protected int getLayoutRs() {
        return R.layout.activity_profile;
    }

    @Override
    protected void init() {
        super.init();
        mPagerTitle.setText("我的资料");
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary));
        Intent intent = getIntent();
        mUserInfo = (UserInfo) intent.getSerializableExtra("userinfo");
        setData();
        mHeadBottomMenu = new HeadBottomMenu(this);
        mLoadDialog = new LoadDialog(this);
        mHeadBottomMenu.setItemClickListener(new HeadBottomMenu.ItemClickListener() {
            @Override
            public void photograph() {
                startTakePhotoByPermissions();
            }

            @Override
            public void photopictur() {
                startImagePick();
            }
        });
    }

    @SuppressLint("StringFormatInvalid")
    private void setData() {
        Glide.with(this)
                .load(mUserInfo.getPortrait())
                .error(R.mipmap.widget_dface)
                .into(mHead);
        mTvName.setText(mUserInfo.getName());
        mTvJionTime.setText(String.format(getString(R.string.jointime), mUserInfo.getJointime()));
        mTvDevelop.setText(String.format(getString(R.string.devplatform), mUserInfo
                .getDevplatform()));
        mTvFrom.setText(String.format(getString(R.string.from), mUserInfo.getFrom()));
        mTvZhuanchang.setText(String.format(getString(R.string.zhuanchang), mUserInfo
                .getExpertise()));
    }


    @OnClick({R.id.iv_back, R.id.head, R.id.btn_signout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.head:
                mHeadBottomMenu.show();
                break;
            case R.id.btn_signout:
                signOut();
                break;
        }
    }

    /**
     * 退出登录
     */
    private void signOut() {
        SpUtil.saveString(Constant.UID, "");
        SpUtil.saveString(Constant.COOKIE, "");
        ToastUtil.showToast("退出登录");
        Intent intent = getIntent();
        //传入退出登录标识返回给我的界面
        intent.putExtra("signout", true);
        setResult(2001, intent);
        finish();
    }


    @Override
    public void onActivityResult(final int requestCode, final int resultCode,
                                 final Intent imageReturnIntent) {
        if (resultCode != Activity.RESULT_OK)
            return;
        switch (requestCode) {
            case ImageUtils.REQUEST_CODE_GETIMAGE_BYCAMERA:
                startActionCrop(origUri);// 拍照后裁剪
                break;
            case ImageUtils.REQUEST_CODE_GETIMAGE_BYCROP:
                startActionCrop(imageReturnIntent.getData());// 选图后裁剪
                break;
            case ImageUtils.REQUEST_CODE_GETIMAGE_BYSDCARD:
                uploadNewPhoto();
                break;
        }
    }

    /**
     * 上传头像
     */
    @Deprecated
    private void uploadNewPhoto() {
        mLoadDialog.setMsg("正在上传...");
        mLoadDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 获取头像缩略图
                    if (!StringUtils.isEmpty(protraitPath) && protraitFile.exists()) {
                        protraitBitmap = ImageUtils
                                .loadImgThumbnail(protraitPath, 200, 200);
                    } else {
                        Utils.runOnUIThread(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtil.showToast("图像不存在，上传失败");
                            }
                        });
                        mLoadDialog.dismiss();
                        return;
                    }
                    OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
                    RequestBody body = new MultipartBody.Builder()
                            .setType(MultipartBody.FORM)
                            .addFormDataPart("resource", protraitFile.getName(), MultipartBody
                                    .create(MediaType.parse("multipart/form-data"), protraitFile))
                            .build();
                    Request request = new Request.Builder()
                            .addHeader("cookie", SpUtil.getString(Constant.COOKIE, ""))
                            .post(body)
                            .url(NetDataApi.PORTRAIT_UPDATE)
                            .build();
                    Response response = okHttpClient.newCall(request).execute();
                    if (response.isSuccessful()) {
                        Utils.runOnUIThread(new Runnable() {
                            @Override
                            public void run() {
                                mHead.setImageBitmap(protraitBitmap);
                                ToastUtil.showToast("上传成功");
                                mLoadDialog.dismiss();
                            }
                        });
                    } else {
                        Utils.runOnUIThread(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtil.showToast("上传失败");
                                mLoadDialog.dismiss();
                            }
                        });
                    }
                    System.out.println(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 请求照相机权限码
     */
    private static final int CAMERA_PERM = 1;

    @AfterPermissionGranted(CAMERA_PERM)
    private void startTakePhotoByPermissions() {
        String[] perms = {Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(ProfileActivity.this, perms)) {
            try {
                startTakePhoto();
            } catch (Exception e) {
                Toast.makeText(ProfileActivity.this, R.string.permissions_camera_error, Toast
                        .LENGTH_LONG).show();
            }
        } else {
            // Request one permission
            EasyPermissions.requestPermissions(this,
                    getResources().getString(R.string.str_request_camera_message),
                    CAMERA_PERM, perms);
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        try {
            startTakePhoto();
        } catch (Exception e) {
            Toast.makeText(ProfileActivity.this, R.string.permissions_camera_error, Toast
                    .LENGTH_LONG).show();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Toast.makeText(ProfileActivity.this, R.string.permissions_camera_error, Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // EasyPermissions handles the request result.
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    /**
     * 选择图片裁剪
     */
    private void startImagePick() {
        Intent intent;
        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "选择图片"),
                    ImageUtils.REQUEST_CODE_GETIMAGE_BYCROP);
        } else {
            intent = new Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "选择图片"),
                    ImageUtils.REQUEST_CODE_GETIMAGE_BYCROP);
        }
    }

    /**
     * 启用照相机
     */
    private void startTakePhoto() {
        Intent intent;
        // 判断是否挂载了SD卡
        String savePath = "";
        String storageState = Environment.getExternalStorageState();
        if (storageState.equals(Environment.MEDIA_MOUNTED)) {
            savePath = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + "/oschina/Camera/";
            File savedir = new File(savePath);
            if (!savedir.exists()) {
                savedir.mkdirs();
            }
        }

        // 没有挂载SD卡，无法保存文件
        if (StringUtils.isEmpty(savePath)) {
            ToastUtil.showToast("无法保存照片，请检查SD卡是否挂载");
            return;
        }

        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss")
                .format(new Date());
        String fileName = "osc_" + timeStamp + ".jpg";// 照片命名
        File out = new File(savePath, fileName);
        Uri uri = Uri.fromFile(out);
        origUri = uri;

        String theLarge = savePath + fileName;

        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent,
                ImageUtils.REQUEST_CODE_GETIMAGE_BYCAMERA);
    }

    /**
     * 拍照后裁剪
     *
     * @param data 原始图片
     */
    private void startActionCrop(Uri data) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(data, "image/*");
        intent.putExtra("output", this.getUploadTempFile(data));
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);// 裁剪框比例
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", CROP);// 输出图片大小
        intent.putExtra("outputY", CROP);
        intent.putExtra("scale", true);// 去黑边
        intent.putExtra("scaleUpIfNeeded", true);// 去黑边
        startActivityForResult(intent,
                ImageUtils.REQUEST_CODE_GETIMAGE_BYSDCARD);
    }


    /**
     * 获取裁剪头像的绝对路径
     *
     * @param uri 照片路径
     * @return
     */
    private Uri getUploadTempFile(Uri uri) {
        String storageState = Environment.getExternalStorageState();
        if (storageState.equals(Environment.MEDIA_MOUNTED)) {
            File savedir = new File(FILE_SAVEPATH);
            if (!savedir.exists()) {
                savedir.mkdirs();
            }
        } else {
            ToastUtil.showToast("无法保存上传的头像，请检查SD卡是否挂载");
            return null;
        }
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String thePath = ImageUtils.getAbsolutePathFromNoStandardUri(uri);

        // 如果是标准Uri
        if (StringUtils.isEmpty(thePath)) {
            thePath = ImageUtils.getAbsoluteImagePath(ProfileActivity.this, uri);
        }
        String ext = FileUtil.getFileFormat(thePath);
        ext = StringUtils.isEmpty(ext) ? "jpg" : ext;
        // 照片命名
        String cropFileName = "osc_crop_" + timeStamp + "." + ext;
        // 裁剪头像的绝对路径
        protraitPath = FILE_SAVEPATH + cropFileName;
        protraitFile = new File(protraitPath);

        return Uri.fromFile(protraitFile);
    }
}
