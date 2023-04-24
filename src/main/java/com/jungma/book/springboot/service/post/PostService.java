package com.jungma.book.springboot.service.post;

import com.jungma.book.springboot.domain.post.Post;
import com.jungma.book.springboot.domain.post.PostRepository;
import com.jungma.book.springboot.web.dto.PostCreateRequest;
import com.jungma.book.springboot.web.dto.PostResponse;
import com.jungma.book.springboot.web.dto.PostUpdateRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long save(PostCreateRequest postCreateRequest) {
        return postRepository.save(postCreateRequest.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostUpdateRequest postUpdateRequest) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        post.update(postUpdateRequest.getTitle(), postUpdateRequest.getContent());
        return id;
    }

    public PostResponse findById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        return new PostResponse(post);
    }
}