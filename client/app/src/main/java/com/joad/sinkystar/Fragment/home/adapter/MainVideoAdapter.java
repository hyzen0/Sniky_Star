package com.joad.sinkystar.Fragment.home.adapter;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.joad.sinkystar.Fragment.home.model.ModelMainVideo;
import com.joad.sinkystar.Fragment.others.Bottom_sheet_option;
import com.joad.sinkystar.Fragment.others.Bottom_sheet_share;
import com.joad.sinkystar.R;
import com.joad.sinkystar.Utility.CacheDataSourceFactory;
import com.joad.sinkystar.Utility.Utility;
import com.squareup.picasso.Picasso;
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;


public class MainVideoAdapter extends RecyclerView.Adapter<MainVideoAdapter.myViewHolder> {


    int flag;
    private ArrayList<ModelMainVideo> properties;
    private Activity mContext;
    private Fragment Mainfragment;
    private Picasso picasso;
    private ViewPager2 viewPager2;


    public MainVideoAdapter(Activity mContext, Fragment MainFragment, ArrayList<ModelMainVideo> properties, ViewPager2 viewPager2, int flag) {
        this.mContext = mContext;
        this.properties = properties;
        this.flag = flag;
        this.Mainfragment = MainFragment;
        this.picasso = Picasso.get();
        this.viewPager2 = viewPager2;

    }


    @Override
    public void onBindViewHolder(final myViewHolder holder, final int position) {

        ModelMainVideo item = properties.get(position);

        holder.mNameSong.setText("This app does not include any chinese product thats only for test text");

        picasso.load(item.getCoverImgUrl()).into(holder.mvideo_thum);


    }


    @Override
    public int getItemCount() {

        return properties.size();


    }


    @Override
    public myViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View v;

        v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_video_main, parent, false);

        return new myViewHolder(v);
    }


    private void moveToCommentFragment(myViewHolder holder, int type) {
        try {
            FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder()
                    .addSharedElement(holder.mAllLayout, "TransAllLayout")
                    .build();

            Bundle bundle = new Bundle();
            bundle.putString("type", "" + type);

            NavOptions.Builder navBuilder = new NavOptions.Builder();
            navBuilder.setEnterAnim(R.anim.animate_slide_up_enter).setExitAnim(R.anim.animate_slide_up_exit)
                    .setPopEnterAnim(R.anim.animate_slide_down_enter).setPopExitAnim(R.anim.animate_slide_down_exit);

            Navigation.findNavController(mContext, R.id.nav_host_fragment)
                    .navigate(R.id.commentFragment, bundle, navBuilder.build(), extras);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onViewAttachedToWindow(@NonNull myViewHolder holder) {

        if (holder.simpleExoPlayer == null) {
            String url = properties.get(holder.getAdapterPosition()).getVideoDownloadUrl();
            if (Utility.getInstance(mContext).getPreferences(mContext, "canPlay").equalsIgnoreCase("" + flag)) {
                holder.intiPlayer(url);
                System.out.println("view attached" + holder.getAdapterPosition());
            }
            holder.parent.setTag(holder);
        } else {
            holder.simpleExoPlayer.play();
            System.out.println("view exop init attach" + holder.getAdapterPosition());
        }

        super.onViewAttachedToWindow(holder);


    }


    @Override
    public void onViewDetachedFromWindow(@NonNull myViewHolder holder) {

        holder.releasePlayer();

        super.onViewDetachedFromWindow(holder);

        System.out.println("view deatttace " + holder.getAdapterPosition());
    }


    public class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView mvideo_thum;
        public FrameLayout mPlayerContainer;

        public PlayerView playerView;
        public ProgressBar progressBar;
        public SimpleExoPlayer simpleExoPlayer;
        //data
        public CircleImageView mCircleImageView2;
        public TextView mNameUser;
        public RelativeLayout mFollowBtnAdd;
        public ImageView mImageView6;
        public TextView mNameSong;
        public CircleImageView mImgSong;
        public RelativeLayout mConstraintLayout;
        public TextView mLikes;
        public TextView mComments;
        public TextView mDownload;
        public TextView mShares;
        public TextView mDistance;
        public LinearLayout mLinearLayout2;
        public ImageButton mBtnLike;
        public ImageButton mBtnComments;
        public ImageButton mBtnDownload;
        public ImageButton mBtnShare;
        public ImageButton mMoreButton;
        public LinearLayout mProfileUser;
        public LinearLayout mAllLayout;
        public ImageView liked_star;
        public View parent;
        DataSource.Factory dataSourceFactory;


        myViewHolder(View view) {
            super(view);
            parent = view;


            mPlayerContainer = view.findViewById(R.id.container);
            progressBar = view.findViewById(R.id.progressBar);
            mvideo_thum = view.findViewById(R.id.video_thum);
            playerView = view.findViewById(R.id.exo_player);


            liked_star = view.findViewById(R.id.liked_star);


            mCircleImageView2 = (CircleImageView) itemView.findViewById(R.id.circleImageView2);
//          mVideoView = itemView.findViewById(R.id.tiktok_View);
            mNameUser = (TextView) itemView.findViewById(R.id.user_name);
            mDistance = (TextView) itemView.findViewById(R.id.distance);
            mDistance.setOnClickListener(this);
            mFollowBtnAdd = (RelativeLayout) itemView.findViewById(R.id.add_follow_btn);
            mFollowBtnAdd.setOnClickListener(this);
            mNameSong = (TextView) itemView.findViewById(R.id.song_name);
            mImgSong = (CircleImageView) itemView.findViewById(R.id.song_img);
            mLikes = (TextView) itemView.findViewById(R.id.likes);
            mLikes.setOnClickListener(this);
            mComments = (TextView) itemView.findViewById(R.id.comments);
            mComments.setOnClickListener(this);
            mDownload = (TextView) itemView.findViewById(R.id.views);
            mDownload.setOnClickListener(this);
            mShares = (TextView) itemView.findViewById(R.id.shares);
            mShares.setOnClickListener(this);
            mLinearLayout2 = (LinearLayout) itemView.findViewById(R.id.linearLayout2);
            mAllLayout = (LinearLayout) itemView.findViewById(R.id.all_layout);

            mNameSong.setSelected(true);

            //image rotating
            ObjectAnimator imageViewObjectAnimator = ObjectAnimator.ofFloat(mImgSong,
                    "rotation", 0f, 360f);
            imageViewObjectAnimator.setRepeatCount(ObjectAnimator.INFINITE);
            imageViewObjectAnimator.setRepeatMode(ObjectAnimator.RESTART);
            imageViewObjectAnimator.setInterpolator(new AccelerateInterpolator());
            imageViewObjectAnimator.setDuration(2000);
            imageViewObjectAnimator.start();


            mBtnLike = (ImageButton) itemView.findViewById(R.id.like_btn);
            mBtnLike.setOnClickListener(this);
            mBtnComments = (ImageButton) itemView.findViewById(R.id.comments_btn);
            mBtnComments.setOnClickListener(this);
            mBtnDownload = (ImageButton) itemView.findViewById(R.id.download_btn);
            mBtnDownload.setOnClickListener(this);
            mBtnShare = (ImageButton) itemView.findViewById(R.id.share_btn);
            mBtnShare.setOnClickListener(this);
            mMoreButton = view.findViewById(R.id.more_vert_option);
            mMoreButton.setOnClickListener(this);
            mImgSong.setOnClickListener(this);
            mNameSong.setOnClickListener(this);


            mProfileUser = (LinearLayout) itemView.findViewById(R.id.user_profile);
            mProfileUser.setOnClickListener(this);

            PushDownAnim.setPushDownAnimTo(mBtnLike).setScale(MODE_SCALE, 0.89f);
            PushDownAnim.setPushDownAnimTo(mComments).setScale(MODE_SCALE, 0.89f);


            final GestureDetector gd = new GestureDetector(mContext, new GestureDetector.SimpleOnGestureListener() {


                //here is the method for double tap


                @Override
                public boolean onDoubleTap(MotionEvent e) {

                    //your action here for double tap e.g.
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    super.onLongPress(e);

                }

                @Override
                public boolean onDoubleTapEvent(MotionEvent e) {
                    return true;
                }

                @Override
                public boolean onDown(MotionEvent motionEvent) {

                    return true;
                }


            });


            gd.setOnDoubleTapListener(new GestureDetector.OnDoubleTapListener() {
                @Override
                public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                    //your action here for double tap e.g.
                    Log.d("OnDoubleTapListener", "onsingletap");

                    if (simpleExoPlayer.isPlaying()) {
                        simpleExoPlayer.pause();
                    } else {
                        simpleExoPlayer.play();
                    }
                    return false;
                }

                @Override
                public boolean onDoubleTap(MotionEvent motionEvent) {
                    //your action here for double tap e.g.
                    Log.d("OnDoubleTapListener", "onDoubleTap");

                    mBtnLike.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_starcolor));

                    liked_star.setVisibility(View.VISIBLE);
                    Utility.getInstance(mContext).AnimationBounce(mContext, liked_star, 0, 50);
                    return false;
                }

                @Override
                public boolean onDoubleTapEvent(MotionEvent motionEvent) {
                    return false;
                }
            });

            playerView.findViewById(R.id.exo_pause).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    return gd.onTouchEvent(event);
                }
            });
            playerView.findViewById(R.id.exo_play).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    return gd.onTouchEvent(event);
                }
            });

            playerView.findViewById(R.id.exo_rew).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (simpleExoPlayer != null) {
                        final long sikpos = simpleExoPlayer.getCurrentPosition() / 10;

                        if (simpleExoPlayer.getCurrentPosition() > sikpos) {
                            simpleExoPlayer.seekTo(simpleExoPlayer.getCurrentPosition() - sikpos);
                        }
                    }
                }
            });
            playerView.findViewById(R.id.exo_ffwd).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (simpleExoPlayer != null) {
                        final long sikpos = simpleExoPlayer.getCurrentPosition() / 10;

                        simpleExoPlayer.seekTo(simpleExoPlayer.getCurrentPosition() + sikpos);

                    }
                }
            });
            if (flag == 2) {
                mDistance.setVisibility(View.VISIBLE);
            }

        }


        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.add_follow_btn:
                    // TODO 20/10/12
                    break;
                case R.id.distance:
                    Navigation.findNavController(mContext, R.id.nav_host_fragment)
                            .navigate(R.id.action_mainFragment_to_placeFragment);

                    break;
                case R.id.song_name:
                case R.id.song_img:
                    Navigation.findNavController(mContext, R.id.nav_host_fragment)
                            .navigate(R.id.action_mainFragment_to_songFragment);

                    break;
                case R.id.like_btn:
                    if (mBtnLike.getDrawable().getConstantState().equals(mContext.getResources().getDrawable(R.drawable.ic_star_line).getConstantState())) {

                        mBtnLike.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_starcolor));

                        liked_star.setVisibility(View.VISIBLE);
                        Utility.getInstance(mContext).AnimationBounce(mContext, liked_star, 0, 50);

                    } else {
                        mBtnLike.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_star_line));
                    }
                    break;
                case R.id.comments_btn:
                case R.id.comments:
                    moveToCommentFragment(myViewHolder.this, 1);
                    break;
                case R.id.download_btn:
                case R.id.views:
                    moveToCommentFragment(myViewHolder.this, 2);
                    break;
                case R.id.likes:
                    moveToCommentFragment(myViewHolder.this, 0);

                    break;


                case R.id.shares:

                    moveToCommentFragment(myViewHolder.this, 3);

                    break;
                case R.id.share_btn:
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("type", "video");
                    Bottom_sheet_share filter_bottom_sheet2 = Bottom_sheet_share.newInstance(mContext, bundle2);
                    filter_bottom_sheet2.show(Mainfragment.getChildFragmentManager(), "tile");

                    break;
                case R.id.user_profile:

                    try {
                        Navigation.findNavController(mContext, R.id.nav_host_fragment)
                                .navigate(R.id.action_mainFragment_to_userProfileFragment);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
                case R.id.more_vert_option:
                    Bundle bundle = new Bundle();
                    bundle.putString("type", "video");
                    Bottom_sheet_option filter_bottom_sheet = Bottom_sheet_option.newInstance(mContext, bundle);
                    filter_bottom_sheet.show(Mainfragment.getChildFragmentManager(), "tile");

                    break;
                default:
                    break;
            }

        }


        //player init....
        public void intiPlayer(String mediaUrl) {
            if (simpleExoPlayer == null) {
                try {

//                    BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter.Builder(mContext).build();
//                    TrackSelection.Factory videoTrackSelectionFactory =
//                            new AdaptiveTrackSelection.Factory(bandwidthMeter);
//                    TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);

//


//                    dataSourceFactory = new DefaultDataSourceFactory(
//                            mContext, Util.getUserAgent(mContext, mContext.getApplicationContext().getPackageName()));

                    simpleExoPlayer = new SimpleExoPlayer.Builder(mContext).build();

//                    String mediaUrl = "https://media.chingari.io/uploads/bd778747-59d0-4d48-983d-5f280e348f26-1601841263418/transcode/p720/bd778747-59d0-4d48-983d-5f280e348f26-1601841263418.mp4";
//                    String mediaUrl = properties.get(getAdapterPosition()).getVideoDownloadUrl();
                    MediaItem mediaItem = MediaItem.fromUri(mediaUrl);


                    dataSourceFactory = new CacheDataSourceFactory(mContext, 100 * 1024 * 1024, 5 * 1024 * 1024);

                    MediaSource videoSource = new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(mediaItem);

                    simpleExoPlayer.addMediaSource(videoSource);
                    simpleExoPlayer.prepare();


//
//                    if (Utility.getInstance(mContext).getPreferences(mContext, "canPlay").equalsIgnoreCase("1")) {
//
//                        simpleExoPlayer.setPlayWhenReady(true);
//                        Utility.getInstance(mContext).addPreferences(mContext, "canPlay", "0");
//
//                    }

                    playerView.setPlayer(simpleExoPlayer);


                    simpleExoPlayer.addListener(new Player.EventListener() {
                        @Override
                        public void onPlaybackStateChanged(int state) {

                            switch (state) {

                                case Player.STATE_BUFFERING:
                                    progressBar.setVisibility(View.VISIBLE);
                                    progressBar.invalidate();
                                    progressBar.setIndeterminate(true);
                                    Log.e("dffsda", "onPlayerStateChanged: Buffering video.");


                                    break;
                                case Player.STATE_ENDED:
                                    Log.d("asdfas", "onPlayerStateChanged: Video ended.");
                                    simpleExoPlayer.seekTo(0);
                                    break;
                                case Player.STATE_IDLE:

                                    Log.e("afasdf", "onPlayerStateChanged: STATE_IDLE.");

                                    intiPlayer(properties.get(getAdapterPosition()).getVideoDownloadUrl());

                                    break;
                                case Player.STATE_READY:
                                    Log.e("dfsasda", "onPlayerStateChanged: Ready to play.");
//
                                    playerView.requestFocus();

                                    mvideo_thum.setVisibility(View.GONE);

                                    progressBar.invalidate();
                                    progressBar.setIndeterminate(false);

                                    break;
                                default:
                                    break;
                            }

                        }
                    });


                    playerView.setControllerHideOnTouch(false);
                    playerView.setControllerHideDuringAds(false);
                    playerView.setControllerAutoShow(true);
                    playerView.setUseController(true);
                    playerView.showController();

                    playerView.setControllerShowTimeoutMs(0);

                } catch (Exception e) {
                    Log.e("MainAcvtivity", " exoplayer error " + e.toString());
                }
            }
        }

        public boolean isPlaying() {
            return simpleExoPlayer != null
                    && simpleExoPlayer.getPlaybackState() != Player.STATE_ENDED
                    && simpleExoPlayer.getPlaybackState() != Player.STATE_IDLE
                    && simpleExoPlayer.getPlayWhenReady();
        }

        public void releasePlayer() {

            if (simpleExoPlayer != null) {

                System.out.println("goin ti release");
                simpleExoPlayer.release();
                mvideo_thum.setVisibility(View.VISIBLE);

                progressBar.invalidate();
                progressBar.setIndeterminate(true);

                simpleExoPlayer = null;
            }
        }

        public void stopPlayer() {
            if (simpleExoPlayer != null) {

                simpleExoPlayer.stop();
            }
        }

        public void pushPlayer() {
            if (simpleExoPlayer != null) {
                System.out.println("sadfasd ::::   pause player");
                simpleExoPlayer.pause();
            }
        }

        public void resumePlayer() {
            System.out.println(" resume goint o " +
                    "");
            if (simpleExoPlayer != null) {
                if (!isPlaying()) {
                    mvideo_thum.setVisibility(View.GONE);
                    simpleExoPlayer.setPlayWhenReady(true);
                    simpleExoPlayer.play();
                }
            } else {
                intiPlayer(properties.get(getAdapterPosition()).getVideoDownloadUrl());
            }
        }

    }


}



