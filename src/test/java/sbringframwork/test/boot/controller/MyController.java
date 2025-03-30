package sbringframwork.test.boot.controller;

import sbringframwork.mvc.annotation.*;
import sbringframwork.test.boot.pojo.MyDTO;

/**
 *
 */
@RestController
public class MyController {
    @RequestMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @GetMapping("/path/{id}")
    public String path(@PathVariable("id") Integer id) {
        return "id:" + id;
    }

    @PostMapping("/post")
    public String post(@RequestBody MyDTO myDTO) {
        return "id是" + myDTO.getId() + ", name是" + myDTO.getName();
    }
}
