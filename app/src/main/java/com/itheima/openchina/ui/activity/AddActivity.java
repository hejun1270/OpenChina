package com.itheima.openchina.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.openchina.R;
import com.itheima.openchina.appcontrol.Constant;
import com.itheima.openchina.bases.BaseActivity;
import com.itheima.openchina.beans.UpLoadImgInfo;
import com.itheima.openchina.emoji.EmojiIcon;
import com.itheima.openchina.emoji.EmojiPageFragment;
import com.itheima.openchina.emoji.EmojiRules;
import com.itheima.openchina.emoji.InputHelper;
import com.itheima.openchina.emoji.OnEmojiClickListener;
import com.itheima.openchina.picture.GlideImageLoader;
import com.itheima.openchina.picture.ImagePickerAdapter;
import com.itheima.openchina.picture.SelectDialog;
import com.itheima.openchina.utils.GsonUtil;
import com.itheima.openchina.utils.SpUtil;
import com.itheima.openchina.utils.ToastUtil;
import com.itheima.openchina.utils.Utils;
import com.itheima.openchina.wedigt.LoadDialog;
import com.jaeger.library.StatusBarUtil;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.imagepicker.view.CropImageView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;
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
 * Date: 2017/11/3 <p/>
 * Time: 13:07 <p/>
 * Project:OpenChina <p/>
 * Package:com.itheima.openchina.ui.activity <p/>
 * Desc:弹一弹activity
 */

public class AddActivity extends BaseActivity implements ImagePickerAdapter
        .OnRecyclerViewItemClickListener, EasyPermissions.PermissionCallbacks {
    /**
     * 返回键
     */
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    /**
     * 标题
     */
    @BindView(R.id.pager_title)
    TextView mPagerTitle;
    /**
     * 发送
     */
    @BindView(R.id.tv_send)
    TextView mTvSend;
    /**
     * 输入框
     */
    @BindView(R.id.et_input_twee)
    EditText mEtInputTwee;
    /**
     * 显示需要上传的图片
     */
    @BindView(R.id.rc_ShowPicture)
    RecyclerView mRcShowPicture;
    /**
     * 点击选择需要上传的图片
     */
    @BindView(R.id.iv_addPicture)
    ImageView mIvAddPicture;
    /**
     * 点击@好友
     */
    @BindView(R.id.iv_addMention)
    ImageView mIvAddMention;
    /**
     * 点击添加topic #***#
     */
    @BindView(R.id.iv_addTopic)
    ImageView mIvAddTopic;
    /**
     * 点击显示emoji表情
     */
    @BindView(R.id.iv_addExpression)
    ImageView mIvAddExpression;
    /**
     * 展示emoji表情的viewpager
     */
    @BindView(R.id.emoji_pager)
    ViewPager mEmojiPager;
    /**
     * 点击切换的qq表情
     */
    @BindView(R.id.tv_qqEmoji)
    TextView mTvQqEmoji;
    /**
     * 点击切换到emoji表情
     */
    @BindView(R.id.tv_Emoji)
    TextView mTvEmoji;
    /**
     * 点击删除输入内容 del
     */
    @BindView(R.id.iv_backspace)
    ImageView mIvBackspace;
    /**
     * 展示表情布局的layout
     */
    @BindView(R.id.ll_emoij)
    LinearLayout mLlEmoij;

    @BindView(R.id.sv_add_show_content)
    ScrollView mScrollView;


    @Override
    protected int getLayoutRs() {
        return R.layout.activity_add;
    }

    @Override
    protected void init() {
        super.init();
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary));
        setEmojiPager();

        //初始化图片选择
        initImagePicker();
        initWidget();

        mScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                View view = getCurrentFocus();
                Utils.hideKeyboard(view.getWindowToken());
                mLlEmoij.setVisibility(View.GONE);
                return false;
            }
        });
        mEtInputTwee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLlEmoij.setVisibility(View.GONE);
            }
        });
    }

    /**
     * 设置展示emoji表情的布局是否显示
     */
    protected void setEmojiIsVisibility() {
        if (mLlEmoij.getVisibility() == View.VISIBLE) {
            mLlEmoij.setVisibility(View.GONE);
        } else {
            mLlEmoij.setVisibility(View.VISIBLE);
        }
    }

    @OnClick({R.id.iv_back, R.id.tv_send, R.id.iv_addPicture, R.id.iv_addMention, R.id
            .iv_addTopic, R.id.iv_addExpression, R.id.tv_qqEmoji, R.id.tv_Emoji, R.id.iv_backspace})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_send:
                sendTwee();
                break;
            case R.id.iv_addPicture:
                startTakePhotoByPermissions();
                break;
            case R.id.iv_addMention:
                ToastUtil.showToast("@");
                break;
            case R.id.iv_addTopic:
                ToastUtil.showToast("#***#");
                break;
            case R.id.iv_addExpression:
                Utils.hideKeyboard(view.getWindowToken());
                SystemClock.sleep(300);
                setEmojiIsVisibility();
                break;
            case R.id.tv_qqEmoji:
                mEmojiPager.setCurrentItem(0);
                mTvEmoji.setTextColor(getResources().getColor(R.color.gray));
                mTvQqEmoji.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;
            case R.id.tv_Emoji:
                mEmojiPager.setCurrentItem(1);
                mTvEmoji.setTextColor(getResources().getColor(R.color.colorPrimary));
                mTvQqEmoji.setTextColor(getResources().getColor(R.color.gray));
                break;
            case R.id.iv_backspace:
                InputHelper.backspace(mEtInputTwee);
                break;
        }
    }

    LoadDialog mLoadDialog;
    String token = "";

    /**
     * 发送弹一弹
     */
    private void sendTwee() {
        mLoadDialog = new LoadDialog(this);
        mLoadDialog.setMsg("正在努力上传...");
        mLoadDialog.show();
        String content = mEtInputTwee.getText().toString();
        int image_count = selImageList.size();
        if (image_count > 0) {
            upLoadImg();
        } else if (!content.equals("")) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    upLoadContent();
                }
            }).start();
        } else {
            ToastUtil.showToast("请输入发送内容");
        }
    }

    /**
     * 上传内容
     */
    private boolean upLoadContent() {
        Editable content = mEtInputTwee.getText();
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        RequestBody body = new FormBody.Builder()
                .add("images", token)
                .add("content", content.toString())
                .build();
        Request request = new Request.Builder()
                .addHeader("cookie", SpUtil.getString(Constant.COOKIE, ""))
                .post(body)
                .url("http://www.oschina.net/action/apiv2/tweet")
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 上传图片
     */
    private void upLoadImg() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    boolean isSuccess = true;
                    for (int i = 0; i < selImageList.size(); i++) {
                        File file = new File(selImageList.get(i).path);
                        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
                        RequestBody body = new MultipartBody.Builder()
                                .setType(MultipartBody.FORM)
                                .addFormDataPart("resource", file.getName(), MultipartBody.create
                                        (MediaType.parse("application/octet-stream"), file))
                                .build();
                        Request request;
                        if (token.equals("")) {
                            request = new Request.Builder()
                                    .addHeader("cookie", SpUtil.getString(Constant.COOKIE, ""))
                                    .post(body)
                                    .url("http://www.oschina.net/action/apiv2/resource_image")
                                    .build();
                        } else {
                            request = new Request.Builder()
                                    .addHeader("token", token)
                                    .addHeader("cookie", SpUtil.getString(Constant.COOKIE, ""))
                                    .post(body)
                                    .url("http://www.oschina.net/action/apiv2/resource_image")
                                    .build();
                        }

                        Response response = okHttpClient.newCall(request).execute();
                        UpLoadImgInfo upLoadImgInfo = GsonUtil.parseJsonToBean(response.body()
                                .string(), UpLoadImgInfo.class);
                        int code = upLoadImgInfo.getCode();
                        if (code == 1) {
                            token = upLoadImgInfo.getResult().getToken();
                        } else {
                            isSuccess = false;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mLoadDialog.dismiss();
                                    ToastUtil.showToast("发送失败!");
                                }
                            });
                            break;
                        }
                    }
                    if (isSuccess) {
                        boolean upLoadContent = upLoadContent();
                        if (upLoadContent) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mLoadDialog.dismiss();
                                    ToastUtil.showToast("发送成功!");
                                    finish();
                                }
                            });
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mLoadDialog.dismiss();
                                    ToastUtil.showToast("发送失败!");
                                }
                            });
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private EmojiPagerAdapter adapter;
    private List<EmojiPageFragment> mFragments;

    /**
     * 设置表情界面
     */
    private void setEmojiPager() {
        mFragments = new ArrayList<>();
        mFragments.add(new EmojiPageFragment(EmojiRules.QQ_TYPE, emojiClickListener));
        mFragments.add(new EmojiPageFragment(EmojiRules.QQ_TYPE, emojiClickListener));
        adapter = new EmojiPagerAdapter(getSupportFragmentManager());
        mEmojiPager.setAdapter(adapter);
        mEmojiPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int
                    positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mTvEmoji.setTextColor(getResources().getColor(R.color.gray));
                        mTvQqEmoji.setTextColor(getResources().getColor(R.color.colorPrimary));

                        break;
                    case 1:
                        mTvEmoji.setTextColor(getResources().getColor(R.color.colorPrimary));
                        mTvQqEmoji.setTextColor(getResources().getColor(R.color.gray));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mEmojiPager.setCurrentItem(0);
        mTvQqEmoji.setTextColor(getResources().getColor(R.color.colorPrimary));
    }

    private OnEmojiClickListener emojiClickListener = new OnEmojiClickListener() {
        @Override
        public void onEmojiClick(EmojiIcon v) {
            InputHelper.input2OSC(mEtInputTwee, v);
        }
    };

    class EmojiPagerAdapter extends FragmentPagerAdapter {

        public EmojiPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }


    /**
     * 请求照相机权限码
     */
    private static final int CAMERA_PERM = 1;

    @AfterPermissionGranted(CAMERA_PERM)
    private void startTakePhotoByPermissions() {
        String[] perms = {Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(AddActivity.this, perms)) {
            try {
                startImagePick();
            } catch (Exception e) {
                Toast.makeText(AddActivity.this, R.string.permissions_camera_error, Toast
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
            startImagePick();
        } catch (Exception e) {
            Toast.makeText(AddActivity.this, R.string.permissions_camera_error, Toast
                    .LENGTH_LONG).show();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Toast.makeText(AddActivity.this, R.string.permissions_camera_error, Toast.LENGTH_LONG)
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
     * 添加图片
     */
    public static final int IMAGE_ITEM_ADD = -1;
    /**
     * 选择图片
     */
    public static final int REQUEST_CODE_SELECT = 100;
    /**
     * 预览图片
     */
    public static final int REQUEST_CODE_PREVIEW = 101;

    private ImagePickerAdapter imageAdapter;
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    private int maxImgCount = 9;               //允许选择图片最大数

    /**
     * 选择图片
     */
    private void startImagePick() {
        //打开选择,本次允许选择的数量
        ImagePicker.getInstance().setSelectLimit(maxImgCount -
                selImageList.size());
        Intent intent1 = new Intent(AddActivity.this,
                ImageGridActivity.class);
        intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES, selImageList);
        startActivityForResult(intent1, REQUEST_CODE_SELECT);
    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(true);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(maxImgCount);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
    }

    private void initWidget() {
        selImageList = new ArrayList<>();
        imageAdapter = new ImagePickerAdapter(this, selImageList, maxImgCount);
        imageAdapter.setOnItemClickListener(this);
        mRcShowPicture.setLayoutManager(new GridLayoutManager(this, 4));
        mRcShowPicture.setHasFixedSize(true);
        mRcShowPicture.setAdapter(imageAdapter);
    }

    /**
     * 展示选择dialog
     *
     * @param listener
     * @param names
     * @return
     */
    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String>
            names) {
        SelectDialog dialog = new SelectDialog(this, R.style
                .transparentFrameWindowStyle,
                listener, names);
        if (!this.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case IMAGE_ITEM_ADD:
                List<String> names = new ArrayList<>();
                names.add("拍照");
                names.add("相册");
                showDialog(new SelectDialog.SelectDialogListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long
                            id) {
                        switch (position) {
                            case 0: // 直接调起相机
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount -
                                        selImageList.size());
                                Intent intent = new Intent(AddActivity.this, ImageGridActivity
                                        .class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); //
                                // 是否是直接打开相机
                                startActivityForResult(intent, REQUEST_CODE_SELECT);
                                break;
                            case 1:
                                startTakePhotoByPermissions();
                                break;
                            default:
                                break;
                        }

                    }
                }, names);


                break;
            default:
                //打开预览
                Intent intentPreview = new Intent(this, ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>)
                        imageAdapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                break;
        }
    }

    ArrayList<ImageItem> images = null;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker
                        .EXTRA_RESULT_ITEMS);
                if (images != null) {
                    selImageList.addAll(images);
                    imageAdapter.setImages(selImageList);
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker
                        .EXTRA_IMAGE_ITEMS);
                if (images != null) {
                    selImageList.clear();
                    selImageList.addAll(images);
                    imageAdapter.setImages(selImageList);
                }
            }
        }
    }
}
