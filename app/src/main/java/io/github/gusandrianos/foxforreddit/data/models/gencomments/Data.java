package io.github.gusandrianos.foxforreddit.data.models.gencomments;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Data{

    @SerializedName("modhash")
    private String modhash;

    @SerializedName("children")
    private List<ChildrenItem> children;

    @SerializedName("before")
    private Object before;

    @SerializedName("dist")
    private Object dist;

    @SerializedName("after")
    private Object after;

    @SerializedName("depth")
    private int depth;

    @SerializedName("parent_id")
    private String parentId;

    @SerializedName("count")
    private int count;

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private String id;

    @SerializedName("body_html")
    private String bodyHtml;

    @SerializedName("author_flair_richtext")
    private List<Object> authorFlairRichtext;

    @SerializedName("saved")
    private boolean saved;

    @SerializedName("controversiality")
    private int controversiality;

    @SerializedName("body")
    private String body;

    @SerializedName("total_awards_received")
    private int totalAwardsReceived;

    @SerializedName("link_id")
    private String linkId;

    @SerializedName("subreddit_id")
    private String subredditId;

    @SerializedName("subreddit")
    private String subreddit;

    @SerializedName("score")
    private int score;

    @SerializedName("mod_reason_title")
    private Object modReasonTitle;

    @SerializedName("is_submitter")
    private boolean isSubmitter;

    @SerializedName("can_gild")
    private boolean canGild;

    @SerializedName("author_premium")
    private boolean authorPremium;

    @SerializedName("locked")
    private boolean locked;

    @SerializedName("created_utc")
    private int createdUtc;

    @SerializedName("likes")
    private Object likes;

    @SerializedName("banned_at_utc")
    private Object bannedAtUtc;

    @SerializedName("downs")
    private int downs;

    @SerializedName("edited")
    private boolean edited;

    @SerializedName("author")
    private String author;

    @SerializedName("created")
    private int created;

    @SerializedName("treatment_tags")
    private List<Object> treatmentTags;

    @SerializedName("author_flair_background_color")
    private String authorFlairBackgroundColor;

    @SerializedName("report_reasons")
    private Object reportReasons;

    @SerializedName("gildings")
    private Gildings gildings;

    @SerializedName("approved_by")
    private Object approvedBy;

    @SerializedName("score_hidden")
    private boolean scoreHidden;

    @SerializedName("replies")
    private Replies replies;

    @SerializedName("subreddit_name_prefixed")
    private String subredditNamePrefixed;

    @SerializedName("mod_reason_by")
    private Object modReasonBy;

    @SerializedName("top_awarded_type")
    private Object topAwardedType;

    @SerializedName("approved_at_utc")
    private Object approvedAtUtc;

    @SerializedName("no_follow")
    private boolean noFollow;

    @SerializedName("ups")
    private int ups;

    @SerializedName("awarders")
    private List<Object> awarders;

    @SerializedName("author_flair_type")
    private String authorFlairType;

    @SerializedName("permalink")
    private String permalink;

    @SerializedName("author_flair_css_class")
    private String authorFlairCssClass;

    @SerializedName("num_reports")
    private Object numReports;

    @SerializedName("mod_reports")
    private List<Object> modReports;

    @SerializedName("gilded")
    private int gilded;

    @SerializedName("author_patreon_flair")
    private boolean authorPatreonFlair;

    @SerializedName("collapsed")
    private boolean collapsed;

    @SerializedName("collapsed_reason")
    private Object collapsedReason;

    @SerializedName("removal_reason")
    private Object removalReason;

    @SerializedName("mod_note")
    private Object modNote;

    @SerializedName("send_replies")
    private boolean sendReplies;

    @SerializedName("author_flair_text")
    private String authorFlairText;

    @SerializedName("archived")
    private boolean archived;

    @SerializedName("author_flair_text_color")
    private String authorFlairTextColor;

    @SerializedName("can_mod_post")
    private boolean canModPost;

    @SerializedName("author_fullname")
    private String authorFullname;

    @SerializedName("subreddit_type")
    private String subredditType;

    @SerializedName("user_reports")
    private List<Object> userReports;

    @SerializedName("associated_award")
    private Object associatedAward;

    @SerializedName("distinguished")
    private Object distinguished;

    @SerializedName("author_flair_template_id")
    private Object authorFlairTemplateId;

    @SerializedName("stickied")
    private boolean stickied;

    @SerializedName("all_awardings")
    private List<Object> allAwardings;

    @SerializedName("comment_type")
    private Object commentType;

    @SerializedName("collapsed_because_crowd_control")
    private Object collapsedBecauseCrowdControl;

    @SerializedName("banned_by")
    private Object bannedBy;

    public String getModhash(){
        return modhash;
    }

    public List<ChildrenItem> getChildren(){
        return children;
    }

    public Object getBefore(){
        return before;
    }

    public Object getDist(){
        return dist;
    }

    public Object getAfter(){
        return after;
    }

    public int getDepth(){
        return depth;
    }

    public String getParentId(){
        return parentId;
    }

    public int getCount(){
        return count;
    }

    public String getName(){
        return name;
    }

    public String getId(){
        return id;
    }

    public String getBodyHtml(){
        return bodyHtml;
    }

    public List<Object> getAuthorFlairRichtext(){
        return authorFlairRichtext;
    }

    public boolean isSaved(){
        return saved;
    }

    public int getControversiality(){
        return controversiality;
    }

    public String getBody(){
        return body;
    }

    public int getTotalAwardsReceived(){
        return totalAwardsReceived;
    }

    public String getLinkId(){
        return linkId;
    }

    public String getSubredditId(){
        return subredditId;
    }

    public String getSubreddit(){
        return subreddit;
    }

    public int getScore(){
        return score;
    }

    public Object getModReasonTitle(){
        return modReasonTitle;
    }

    public boolean isIsSubmitter(){
        return isSubmitter;
    }

    public boolean isCanGild(){
        return canGild;
    }

    public boolean isAuthorPremium(){
        return authorPremium;
    }

    public boolean isLocked(){
        return locked;
    }

    public int getCreatedUtc(){
        return createdUtc;
    }

    public Object getLikes(){
        return likes;
    }

    public Object getBannedAtUtc(){
        return bannedAtUtc;
    }

    public int getDowns(){
        return downs;
    }

    public boolean isEdited(){
        return edited;
    }

    public String getAuthor(){
        return author;
    }

    public int getCreated(){
        return created;
    }

    public List<Object> getTreatmentTags(){
        return treatmentTags;
    }

    public String getAuthorFlairBackgroundColor(){
        return authorFlairBackgroundColor;
    }

    public Object getReportReasons(){
        return reportReasons;
    }

    public Gildings getGildings(){
        return gildings;
    }

    public Object getApprovedBy(){
        return approvedBy;
    }

    public boolean isScoreHidden(){
        return scoreHidden;
    }

    public Replies getReplies(){
        return replies;
    }

    public String getSubredditNamePrefixed(){
        return subredditNamePrefixed;
    }

    public Object getModReasonBy(){
        return modReasonBy;
    }

    public Object getTopAwardedType(){
        return topAwardedType;
    }

    public Object getApprovedAtUtc(){
        return approvedAtUtc;
    }

    public boolean isNoFollow(){
        return noFollow;
    }

    public int getUps(){
        return ups;
    }

    public List<Object> getAwarders(){
        return awarders;
    }

    public String getAuthorFlairType(){
        return authorFlairType;
    }

    public String getPermalink(){
        return permalink;
    }

    public String getAuthorFlairCssClass(){
        return authorFlairCssClass;
    }

    public Object getNumReports(){
        return numReports;
    }

    public List<Object> getModReports(){
        return modReports;
    }

    public int getGilded(){
        return gilded;
    }

    public boolean isAuthorPatreonFlair(){
        return authorPatreonFlair;
    }

    public boolean isCollapsed(){
        return collapsed;
    }

    public Object getCollapsedReason(){
        return collapsedReason;
    }

    public Object getRemovalReason(){
        return removalReason;
    }

    public Object getModNote(){
        return modNote;
    }

    public boolean isSendReplies(){
        return sendReplies;
    }

    public String getAuthorFlairText(){
        return authorFlairText;
    }

    public boolean isArchived(){
        return archived;
    }

    public String getAuthorFlairTextColor(){
        return authorFlairTextColor;
    }

    public boolean isCanModPost(){
        return canModPost;
    }

    public String getAuthorFullname(){
        return authorFullname;
    }

    public String getSubredditType(){
        return subredditType;
    }

    public List<Object> getUserReports(){
        return userReports;
    }

    public Object getAssociatedAward(){
        return associatedAward;
    }

    public Object getDistinguished(){
        return distinguished;
    }

    public Object getAuthorFlairTemplateId(){
        return authorFlairTemplateId;
    }

    public boolean isStickied(){
        return stickied;
    }

    public List<Object> getAllAwardings(){
        return allAwardings;
    }

    public Object getCommentType(){
        return commentType;
    }

    public Object getCollapsedBecauseCrowdControl(){
        return collapsedBecauseCrowdControl;
    }

    public Object getBannedBy(){
        return bannedBy;
    }
}