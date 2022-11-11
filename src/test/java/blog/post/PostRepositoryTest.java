package blog.post;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;


@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostRepositoryTest {
    @Autowired
    public PostRepository postRepository;

    @BeforeEach
    void clear(){
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("Test Add Post")
    void addTest(){
        Post post = new Post(new String[]{"hello", "world"});

        Post ret = postRepository.save(post);

        Assertions.assertThat(post.getContent()).isEqualTo(ret.getContent());
    }

    @Test
    void editTest(){
        Post post = new Post(new String[]{"hello", "world"});

        Post ret = postRepository.save(post);
        String[] content = post.getContent();
        for(int i=0; i<content.length; i++){
            content[i] += "2";
        }

        postRepository.save(ret);

        Post found = postRepository.findById(ret.getId()).get();

        Assertions.assertThat(found.getId().equals(ret.getId()));
        Assertions.assertThat(Arrays.equals(found.getContent(), ret.getContent()));
        Assertions.assertThat(found.getCreatedAt().equals(ret.getCreatedAt()));
    }
    @Test
    void getPostsTest(){
        Post post1 = new Post(new String[]{"hello", "world"});
        Post post2 = new Post(new String[]{"hello2", "world2"});
        Post post3 = new Post(new String[]{"hello3", "world3"});

        postRepository.save(post1);
        postRepository.save(post2);
        postRepository.save(post3);

        List<Post> found = postRepository.findAllByOrderByCreatedAtDesc();

        Assertions.assertThat(found.size()).isEqualTo(3);
        Assertions.assertThat(found.get(0).getContent()).isEqualTo(post3.getContent());
        Assertions.assertThat(found.get(1).getContent()).isEqualTo(post2.getContent());
        Assertions.assertThat(found.get(2).getContent()).isEqualTo(post1.getContent());
    }

}