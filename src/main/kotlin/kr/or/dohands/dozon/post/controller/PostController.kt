package kr.or.dohands.dozon.post.controller

import kr.or.dohands.dozon.core.responsetemplate.Response
import kr.or.dohands.dozon.post.controller.data.PostRequest
import kr.or.dohands.dozon.post.domain.Posts
import kr.or.dohands.dozon.post.service.PostService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/post")
class PostController(
    private val postService: PostService
) {

    @GetMapping
    fun all(): Response<List<Posts>> {
        val posts = postService.findAll()

        return Response(200, posts, "게시판 전체 조회입니다.")
    }

    @GetMapping("{id}")
    fun get(@RequestParam id: Long): Response<Posts>{
        val post = postService.findById(id)

        return Response(200, post, "게시글 조회입니다.")
    }

    @PostMapping
    fun create(@RequestBody createRequest: PostRequest.create) : Response<String>{
        postService.save(createRequest)

        return Response(200, "", "게시글 추가가 완료되었습니다.")
    }

    @DeleteMapping("{id}")
    fun delete(@RequestParam id: Long) : Response<String>{
        postService.deleteById(id)

        return Response(200, "", "게시글 삭제가 완료되었습니다.")
    }

    @PatchMapping
    fun update(@RequestBody updatePostRequest: PostRequest.update) : Response<String>{
        postService.updateById(updatePostRequest)

        return Response(200, "", "게시글 수정이 완료되었습니다.")
    }

}