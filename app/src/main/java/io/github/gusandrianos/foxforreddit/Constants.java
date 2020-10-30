package io.github.gusandrianos.foxforreddit;

import android.content.res.Resources;

public class Constants {
    // Activities
    public static final String REDIRECT_URI = "https://gusandrianos.github.io/login";
    public static final int LAUNCH_SECOND_ACTIVITY = 1;
    public static final String CLIENT_ID = "n1R0bc_lPPTtVg";
    public static final String BASE_OAUTH_URL = "https://www.reddit.com/api/v1/authorize.compact";
    public static final String STATE = "foxForReddit";

    // Post adapter
    public static final String POST_SUBREDDIT = "PostSubreddit";
    public static final String POST_USER = "PostUser";
    public static final String POST_VOTE_UP = "PostVoteUp";
    public static final String POST_VOTE_DOWN = "PostVoteDown";
    public static final String POST_COMMENTS_NUM = "PostCommentsNum";
    public static final String POST_SHARE = "PostShare";
    public static final String POST_THUMBNAIL = "PostThumbnail";
    public static final String POST_VOTE_NOW = "PostVoteNow";
    public static final String POST_ITEM = "PostItem";

    // Reddit paging source
    public static final String STARTER_PAGE = "";
    public static final int MODE_POST = 1;
    public static final int MODE_SUBREDDIT = 2;

    // Post types
    public static final int SELF = 1;
    public static final int LINK = 2;
    public static final int IMAGE = 3;
    public static final int VIDEO = 4;
    public static final int POLL = 5;
    public static final int COMMENT = 6;

    // Subreddit Repository
    public static final int ACTION_SUBSCRIBE = 0;
    public static final int ACTION_UNSUBSCRIBE = 1;

    //Video Type
    public static final int REDDIT_VIDEO = 1;
    public static final int PLAYABLE_VIDEO = 2;
    public static final int UNPLAYABLE_VIDEO = 3;

    //Image Type
    public static final int IS_IMAGE= 1;
    public static final int IS_GALLERY = 2;
    public static final int IS_GIF = 3;

    //Screen Mode
    public static final  int NORMAL_SCREEN = 0;
    public static final  int FULLSCREEN = 1;
}
