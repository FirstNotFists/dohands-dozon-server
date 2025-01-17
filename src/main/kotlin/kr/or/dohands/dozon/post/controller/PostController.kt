package kr.or.dohands.dozon.post.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import kr.or.dohands.dozon.core.responsetemplate.Response
import kr.or.dohands.dozon.post.controller.data.PostRequest
import kr.or.dohands.dozon.post.domain.Posts
import kr.or.dohands.dozon.post.service.PostService
import kr.or.dohands.dozon.post.service.PostServiceImpl
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/notice")
class PostController(
    private val postService: PostService
) {

    @GetMapping
    @Operation(summary = "게시글 전체 조회 요청" , description = "id(number), title(string), content(string), date(LocalDateTIme 인데 string일듯?)")
    fun all(): Response<List<Posts>> {
        val posts = postService.findAll()
        return Response(200, posts, "게시판 전체 조회입니다.")
    }

    @GetMapping("{id}")
    @ApiResponse(responseCode = "203", description = "게시글 번호가 삭제되거나 해당 게시글이 존재하지 않는 예외입니다.")
    fun get(@PathVariable id: Long): Response<Posts>{
        val post = postService.findById(id)
        return Response(200, post, "게시글 상세 조회입니다.")
    }

    @PostMapping
    @Operation(summary = "게시글 작성 요청", description = "title(String), content(String) 을 body로 전송하시면 됩니다")
    fun create(@RequestBody createRequest: PostRequest.body) : Response<String>{
        postService.save(createRequest)
        return Response(200, "", "게시글 추가가 완료되었습니다.")
    }

    @DeleteMapping("{id}")
    @Operation(summary = "게시글 삭제 요청", description = "id param으로 전송하시면 됩니다")
    fun delete(@PathVariable id: Long) : Response<String>{
        postService.deleteById(id)
        return Response(200, "", "게시글 삭제가 완료되었습니다.")
    }

    @PatchMapping("{id}")
    @Operation(summary = "게시글 수정 요청", description = "title(String), content(String) 을 body로 id param으로 전송하시면 됩니다.")
    fun update(@PathVariable id: Long, @RequestBody request: PostRequest.body) : Response<String>{
        postService.update(id, request)
        return Response(200, "", "게시글 수정이 완료되었습니다.")
    }

}