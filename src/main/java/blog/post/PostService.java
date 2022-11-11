package blog.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private PostRepository postRepository;
    private final S3Service s3Service;

    @Autowired
    public PostService(PostRepository postRepository, S3Service s3Service){
        this.postRepository = postRepository;
        this.s3Service = s3Service;
    }

    public Post createPost(Post post){
        String[] contents = post.getContent();

        for(int i=0; i<contents.length; i++){
            String s  = contents[i];
            if(s.startsWith("data:")){
                byte[] image = org.apache.commons.codec.binary.Base64.decodeBase64((s.substring(s.indexOf(",")+1)).getBytes());

                s = s3Service.upload(image);
            }
            contents[i] = s;
        }

        return postRepository.save(post);
    }

    public List<Post> getPosts(){
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

//    public Post getPost() {return postRepository.getPost();}
//
//    public Post getPost(int id) {return postRepository.getPost(id);}
//
    public void editPost(Post post){
        postRepository.save(post);
    }
}
