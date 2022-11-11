package blog.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    final private PostService postService;

    @Autowired
    PostController(PostService postService){
        this.postService = postService;
    }

//    @GetMapping("/post")
//    public Post getPost(@RequestParam (required = false) Integer id)
//    {
////        if(id != null) return postService.getPost(id);
////
////        return postService.getPost();
//    }

    @GetMapping("/posts")
    public List<Post> getAllPost()
    {
        return postService.getPosts();
    }

    @PostMapping("/post")
    public Post createPost(@RequestBody Post post)
    {
        return postService.createPost(post);
    }

    @PostMapping("/editPost")
    public void editPost(@RequestBody Post post){
        postService.editPost(post);
    }
}
