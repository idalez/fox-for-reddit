package io.github.gusandrianos.foxforreddit.data.models.gene;

import io.github.gusandrianos.foxforreddit.data.models.gencomments.Comments;
import io.github.gusandrianos.foxforreddit.data.models.genpost.Post;

public class SinglePost {

   private Post post;
   private Comments comments;

   public SinglePost( Comments comments) {
      this.comments = comments;
   }

//   public Post getPost() {
//      return post;
//   }

//   public void setPost(Post post) {
//      this.post = post;
//   }

   public Comments getComments() {
      return comments;
   }

   public void setComments(Comments comments) {
      this.comments = comments;
   }
}
