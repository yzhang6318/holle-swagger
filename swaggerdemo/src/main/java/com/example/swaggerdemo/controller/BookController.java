package com.example.swaggerdemo.controller;

import com.example.swaggerdemo.entity.Book;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;

@RestController
@RequestMapping("/books")
public class BookController {

    private Map<Long, Book> books = Collections.synchronizedMap(new HashMap<Long, Book>());

    @ApiOperation(value = "获取图书列表", notes = "获取图书列表")
    //@RequestMapping(value={"/getBooks"}, method= RequestMethod.GET)
    @GetMapping("/getBooks")
    public List<Book> getBook() {
        List<Book> book = new ArrayList<>(books.values());
        return book;
    }

    @ApiOperation(value = "创建图书信息", notes = "创建图书信息")
    @ApiImplicitParam(name = "book", value = "图书详细实体", required = true, dataType = "Book")
    //@RequestMapping(value="/postBooks", method= RequestMethod.POST)
    @PostMapping("/postBooks")
    public String postBook(@RequestBody Book book) {
        books.put(book.getId(), book);
        return "success";
    }

    @ApiOperation(value = "获图书细信息", notes = "根据url的id来获取详细信息")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long", paramType = "path")
    //@RequestMapping(value="/getBooks/{id}", method=RequestMethod.GET)
    @GetMapping("/getBooks/{id}")
    public Book getBook(@PathVariable Long id) {
        return books.get(id);
    }

    @ApiOperation(value = "更新图书信息", notes = "根据url的id来指定更新图书信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "图书ID", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name = "book", value = "图书实体book", required = true, dataType = "Book")
    })
    //@RequestMapping(value="/putBooks/{id}", method= RequestMethod.PUT)
    @PutMapping("/putBook/{id}")
    public String putUser(@PathVariable final Long id, @RequestBody final Book book) {
        Book book1 = books.get(id);
        book1.setName(book.getName());
        books.put(id, book1);
        return "success";
    }


    @ApiOperation(value = "删除图书信息", notes = "根据url的id来指定删除图书")
    @ApiImplicitParam(name = "id", value = "图书ID", required = true, dataType = "Long", paramType = "path")
    //@RequestMapping(value="/deleteBook/{id}", method=RequestMethod.DELETE)
    @DeleteMapping("/deleteBook/{id}")
    public String deleteUser(@PathVariable final Long id) {
        books.remove(id);
        return "success";
    }


    @ApiIgnore//使用该注解忽略这个API
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public String jsonTest() {
        return " hi you!";
    }

}
