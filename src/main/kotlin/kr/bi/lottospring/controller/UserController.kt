package kr.bi.lottospring.controller

import kr.bi.lottospring.entity.Users
import kr.bi.lottospring.service.UserService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService,
) {
    @RequestMapping
    fun getUser(): Users {
        return userService.findByNameQuerydsl("jayden")
    }
}
