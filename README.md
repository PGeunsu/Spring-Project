# ๐ Spring-Project
Spring Boot ์น์ฌ์ดํธ (๊ฐ์ธ ํ๋ก์ ํธ)

<br>

## ๐ฅ๏ธ ํ๋ก์ ํธ ์๊ฐ
์คํ๋ง ๋ถํธ + JPA๋ฅผ ํ์ฉํ ์น ์ผํ๋ชฐ ์๋๋ค.

<br>

> ๐บ ๐ฅ [๊ธฐ๋ฅ ๊ตฌํ ์์](https://drive.google.com/file/d/1vrDuOA7JlgbuLWsEo7FczTv3B0jNf8ZF/view?usp=share_link)

<br>

## ๐ ํ๋ก์ ํธ ๊ธฐ๊ฐ
22.12.14 ~ 22.12.26 (1์ธ)

<br>

## โจ๏ธ ๊ฐ๋ฐ ํ๊ฒฝ
* OS : Mac
* Build : Gradle
* Tool : IntelliJ Ultimate , MySql WorkBench, Git
* Front-End : Html, CSS(BootStrap) , JavaSCript, Thymeleaf
* Back-End : Java(1.8.0) Spring Boot Spring Security, Spring Data JPA
* DataBase : MySQL

<br>

## ๐ Dependencies
* Spring Boot DevTools
* Lombok
* Spring Data JPA
* Spring Security
* Spring Web
* Thymeleaf
* QueryDsl
* Spring Validaiton

## ๐ฑ๏ธ ๊ธฐ๋ฅ ๊ตฌํ
1. ํ์๊ฐ์ ๋ฐ ๋ก๊ทธ์ธ 
2. Form ์กฐ๊ฑด ๋ฏธ๋ฌ์ ์๋ฌ ๋ฉ์ธ์ง ์ถ๋ ฅ
3. ๊ฒ์ํ ๊ธฐ๋ฅ
4. ์ํ ๋ฑ๋ก ๋ฐ ์์ , ์ญ์  ๊ธฐ๋ฅ
5. ์ํ ์ด๋ฏธ์ง ๋ฑ๋ก ๊ธฐ๋ฅ
6. ์ํ ๋ฑ๋ก ๋ฐ ์์ , ์ญ์  ๊ธฐ๋ฅ
7. ์ํ ๋ฆฌ์คํธ ์ถ๋ ฅ ๋ฐ ๊ฒ์ ๊ธฐ๋ฅ
8. ์ฅ๋ฐ๊ตฌ๋ ๊ธฐ๋ฅ
9. ์ฃผ๋ฌธ ๊ธฐ๋ฅ

<br>

## ๐ก ์์ธ ๊ธฐ๋ฅ ์ค๋ช ๐ก

### ๋ชฉ์ฐจ
* [๋ก๊ทธ์ธ ๊ธฐ๋ฅ](#-๋ก๊ทธ์ธ-๊ธฐ๋ฅ)
* [๊ฒ์๊ธ ๊ธฐ๋ฅ](#-๋ก๊ทธ์ธ-๊ธฐ๋ฅ)
* [์ํ ๋ฑ๋ก ๋ฐ ์์  ๊ธฐ๋ฅ](#-์ํ-๋ฑ๋ก-๋ฐ-์์ -๊ธฐ๋ฅ)
* [์ด๋ฏธ์ง ๋ฑ๋ก ๋ฐ ์์  ๊ธฐ๋ฅ](#-์ด๋ฏธ์ง-๋ฑ๋ก-๋ฐ-์์ -๊ธฐ๋ฅ)
* [์ํ ์์ธ์ ๋ณด](#-์ํ-์์ธ์ ๋ณด)
* [์ํ ์ฃผ๋ฌธ ๊ธฐ๋ฅ](#-์ํ-์ฃผ๋ฌธ-๊ธฐ๋ฅ)
* [์ฅ๋ฐ๊ตฌ๋ ๊ธฐ๋ฅ](#-์ฅ๋ฐ๊ตฌ๋-๊ธฐ๋ฅ)
* [์ํ ์ทจ์ ๊ธฐ๋ฅ](#-์ํ-์ทจ์-๊ธฐ๋ฅ)

## ๐ ๋ก๊ทธ์ธ ๊ธฐ๋ฅ
![แแฉแแณแแตแซ แแตแแณแผ](https://user-images.githubusercontent.com/110580350/210503873-1abf4afb-e2d1-438c-9d09-84144bf7d885.gif)

<br>

### ํ์๊ฐ์
#### Controller
```java
@GetMapping("/new")
    public String memberForm(Model model){
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "member/memberForm";
    }

    @PostMapping("/new")
    public String memberForm(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            return "member/memberForm";
        }
        //๊ฒ์ฆํ๋ ๊ฐ์ฒด์์์ @Valid ์ด๋ธํ์ด์ ์ ์ธ, ํ๋ผ๋ฏธํฐ๋ก bindingResult ๊ฐ์ฒด ์ถ๊ฐ (๊ฒ์ฌ ํ ๊ฒฐ๊ณผ๋ bindingResult ์ ๋ด์๋ )
        //hasError()๋ฅผ ํธ์ถํ์ฌ ์๋ฌ๊ฐ ์๋ค๋ฉด ๋ค์ ํ์๊ฐ์ ํ์ด์ง๋ก ์ด๋
        try {
            Member member = Member.createMember(memberFormDto, passwordEncoder);
            memberService.saveMember(member);
        }catch(IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "member/memberForm";
        }

        return "redirect:/";
    }
```
#### Service
```java
public Member saveMember(Member member){
        validateDuplicateMember(member); //ํ์ ์ค๋ณต ๋ฉ์๋  
        return memberRepository.save(member); //Repository ์ member ์ ์ฅ 
    }

    public void validateDuplicateMember(Member member){ 
        Member findMember = memberRepository.findByEmail(member.getEmail());
        //JPA Repository ๋ฅผ ํ์ฉํด ์ด๋ฉ์ผ ์ ๋ณด๋ฅผ ๊ฐ์ ธ์ ์ค๋ณต ํ์ธ
        if(findMember != null){
            throw new IllegalStateException("์ด๋ฏธ ๊ฐ์๋ ํ์์๋๋ค.");
        }
    }  
```

#### Status
```java
public enum Role {

    USER,ADMIN
}

member.setRole(Role.USER); //ํ์ ๊ฐ์ํ  ๋ ์ํ๋ฅผ USER๋ก ์ ์ฅ
```

#### MemberForm
```java
//ํ์๊ฐ์์ค์ ํด๋น ์กฐ๊ฑด ๋ฏธ๋ฌ ์ ์๋ฌ ๋ฉ์ธ์ง ์ถ๋ ฅ
@NotBlank(message = "์ด๋ฆ์ ํ์ ์๋ ฅ ๊ฐ์๋๋ค.")
private String name;
@NotEmpty(message = "์ด๋ฉ์ผ์ ํ์ ์๋ ฅ ๊ฐ์๋๋ค.")
@Email(message = "์ด๋ฉ์ผ ํ์์ผ๋ก ์๋ ฅํด์ฃผ์ธ์")
private String email;
@NotEmpty(message = "๋น๋ฐ๋ฒํธ๋ ํ์ ์๋ ฅ ๊ฐ์๋๋ค.")
@Length(min = 8, max = 16, message = "๋น๋ฐ๋ฒํธ๋ 8์ ์ด์ 16์ ์ดํ๋ก ์๋ ฅํด์ฃผ์ธ์")
private String password;
@NotEmpty(message = "์ฃผ์๋ ํ์ ์๋ ฅ ๊ฐ์๋๋ค.")
private String address;
```
<br>

### ๋ก๊ทธ์ธ

#### Controller
```java
@GetMapping("/login")
    public String loginMember(){
        return "/member/memberLoginForm";
    }
    @GetMapping("/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg","์์ด๋ ๋๋ ๋น๋ฐ๋ฒํธ๋ฅผ ํ์ธํด์ฃผ์ธ์");
        return "/member/memberLoginForm";
    }
}
```

#### Security
```java
@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/members/login") //๋ก๊ทธ์ธ ํ์ด์ง url ์ค์ 
                .defaultSuccessUrl("/") //๋ก๊ทธ์ธ ์ฑ๊ณต ์ ํด๋น url ๋ฐํ
                .usernameParameter("email") //๋ก๊ทธ์ธ ์ ์ฌ์ฉํ  ํ๋ผ๋ฏธํฐ ์ด๋ฆ์ผ๋ก email ์ค์ 
                .failureUrl("/members/login/error") //๋ก๊ทธ์ธ ์คํจ ์ ์ด๋ํ  Url
                .and()
                .logout() //๋ก๊ทธ์์ url
                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
                .logoutSuccessUrl("/") //๋ก๊ทธ์์ ์ฑ๊ณต ์ ์ด๋ํ  url
        ;
      }
```

<br>

## ๐ ๊ฒ์๊ธ ๊ธฐ๋ฅ

![แแฆแแตแแณแฏ แแตแแณแผ](https://user-images.githubusercontent.com/110580350/210507419-4237c26f-bfc3-432e-9a85-4b0803942d91.gif)

### ๊ฒ์๊ธ ๋ฑ๋ก

#### Controller
```java
 @GetMapping("/new")
    public String newArticleForm(Model model){
         model.addAttribute("articleFormDto",new ArticleFormDto());
         return "article/articleForm";
     }

     @PostMapping("/new")
    public String createArticle(@Valid ArticleFormDto articleFormDto, BindingResult bindingResult, Model model){

         if(bindingResult.hasErrors()){
             return "article/articleForm";
         }
         try{
            articleService.saveArticle(articleFormDto);
         }catch (Exception e){
             model.addAttribute("errorMessage","์๋ฌ ๋ฐ์");
             return "article/articleForm";
         }

         return "redirect:/article/index" ;
     }
```
#### Service

```java
public Long saveArticle(ArticleFormDto articleFormDto){

        Article article = articleFormDto.createArticle();
        articleRepository.save(article);

        return article.getId();
    }
```
<br>

### ๊ฒ์๊ธ ์์  ๋ฐ ์ญ์ 

#### Controller

```java
  @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model){

         try{
             Article articleEntity = articleRepository.findById(id).orElse(null);
             model.addAttribute("articleFormDto",articleEntity);
         }catch (Exception e){
             model.addAttribute("errorMessage","์๋ฌ๋ฐ์");
             model.addAttribute("articleFormDto", new ArticleFormDto());
             return "article/articleForm";
         }

         return "article/articleForm";
     }

     @PostMapping("/{id}/edit")
    public String update(@Valid ArticleFormDto articleFormDto, BindingResult bindingResult, Model model){

         if(bindingResult.hasErrors()){
             return "article/articleForm";
         }
         try{
             articleService.updateArticle(articleFormDto);
         }catch (Exception e){
             model.addAttribute("errorMessage","๊ฒ์๋ฌผ ์์  ์ค ์ค๋ฅ๊ฐ ๋ฐ์ํ์ต๋๋ค.");
             return "article/articleForm";
         }
         return "redirect:/";
     }

     @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id){
         Article target = articleRepository.findById(id).orElse(null);

         if(target != null){
             articleRepository.delete(target);
         }
         return "redirect:/article/index";
     }
 ```
 
 #### Service
 
 ```java
 public Long updateArticle(ArticleFormDto articleFormDto) throws Exception{

        Article article = articleRepository.findById(articleFormDto.getId()).orElseThrow(EntityExistsException::new);
        article.updateArticle(articleFormDto);

        return article.getId();
    }
 ```
 
 ### ๊ฒ์๊ธ ๋ชฉ๋ก ๋ฐ ์์ธ ํ์ด์ง
 
 ```java
 @GetMapping("/{articleId}")
    public String show(@PathVariable("articleId") Long id, Model model){

         try{
             Article articleEntity = articleRepository.findById(id).orElse(null);
             model.addAttribute("articleForm", articleEntity);
             System.out.println(id);
         }catch(Exception e){
             model.addAttribute("errorMessage", "์กด์ฌํ์ง ์๋ ์ํ ์๋๋ค.");
             model.addAttribute("articleForm", new ArticleFormDto());
         }

         return "article/show";
     }

     @GetMapping("/index")
    public String index(Model model){
         List<Article> articleEntiyList = articleRepository.findAll();

         model.addAttribute("articleList",articleEntiyList);

         return "article/index";
     }
 ```
 
<br>

## ๐ ์ํ ๋ฑ๋ก ๋ฐ ์์  ๊ธฐ๋ฅ
![แแกแผแแฎแท แแณแผแแฉแจ](https://user-images.githubusercontent.com/110580350/210509320-672d7a0e-7678-4f2a-94c2-398aed9aaf16.gif)

### ์ํ ๋ฑ๋ก

#### Controller
```java
 @GetMapping("/admin/item/new")
    public String ItemForm(Model model){
        model.addAttribute("itemFormDto", new ItemFormDto());
        return "item/itemForm";
    }

    @PostMapping("/admin/item/new")
    public String itemNew(@Valid ItemFormDto itemFormDto, BindingResult bindingResult,
                          Model model, @RequestParam("itemImgFile")List<MultipartFile> itemImgFileList){

        if (bindingResult.hasErrors()) {
            return "item/itemForm";
        }
        if(itemImgFileList.get(0).isEmpty() && itemFormDto.getId() == null){
            model.addAttribute("errorMessage","์ฒซ๋ฒ ์งธ ์ํ ์ด๋ฏธ์ง๋ ํ์ ์๋ ฅ ๊ฐ์๋๋ค.");
            return "item/itemForm";
        }

        try{
            itemService.saveItem(itemFormDto, itemImgFileList);
        }catch(Exception e){
            model.addAttribute("errorMessage","์ํ ๋ฑ๋ก ์ค ์๋ฌ๊ฐ ๋ฐ์ํ์์ต๋๋ค.");
            return "item/itemForm";
        }
        return "redirect:/";
    }
```

#### Service

```java
public Long saveItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws Exception{
        //์ํ ๋ฑ๋ก
        Item item = itemFormDto.createItem();
        itemRepository.save(item);

        //์ด๋ฏธ์ง ๋ฑ๋ก
        for(int i=0;i<itemImgFileList.size();i++){
            ItemImg itemImg = new ItemImg();
            itemImg.setItem(item);

            if(i == 0)
                itemImg.setRepImgYn("Y"); //์ฒซ๋ฒ ์จฐ ์ด๋ฏธ์ง๋ฅผ Y๋ฅผ ์ฃผ๊ณ  ๋ํ์ด๋ฏธ์ง ์ค์ 
            else
                itemImg.setRepImgYn("N");

            itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
            //์ํ ์ด๋ฏธ์ง๋ฅผ ์ ์ฅ
        }

        return item.getId();
    }
```

<br>

### ์ํ ์์ 

#### Controller
```java
 @GetMapping("/admin/item/{itemId}")
    public String itemDtl(@PathVariable("itemId") Long itemId, Model model){
        try{
            ItemFormDto itemFormDto = itemService.getItemDtl(itemId);
            model.addAttribute("itemFormDto",itemFormDto);
        }catch(EntityNotFoundException e){
            model.addAttribute("errorMessage", "์กด์ฌํ์ง ์๋ ์ํ ์๋๋ค.");
            model.addAttribute("itemFormDto",new ItemFormDto());
            return "item/itemForm";
        }
        return "item/itemForm";
    }
@PostMapping("/admin/item/{itemId}")
    public String itemUpdate(@Valid ItemFormDto itemFormDto, BindingResult bindingResult,
                             @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList, Model model){
        if(bindingResult.hasErrors()){
            return "item/itemForm";
        }
        if(itemImgFileList.get(0).isEmpty() && itemFormDto.getId() == null){
            model.addAttribute("errorMessage", "์ฒซ๋ฒ ์งธ ์ํ ์ด๋ฏธ์ง๋ ํ์ ์๋ ฅ ๊ฐ์๋๋ค.");
            return "item/itemForm";
        }
        try{
            itemService.updateItem(itemFormDto, itemImgFileList);
        }catch (Exception e){
            model.addAttribute("errorMessage","์ํ ์์  ์ค ์๋ฌ๊ฐ ๋ฐ์ํ์์ต๋๋ค.");
            return "item/itemForm";
        }
        return "redirect:/";
    }
```

#### Service
```java
 @Transactional(readOnly = true) //ํธ๋์ ์์ ์ฝ๊ธฐ ์ ์ฉ์ผ๋ก ์ค์ 
    //Jpa ๊ฐ ๋ํฐ์ฒดํน(๋ณ๊ฒฝ ๊ฐ์ง)์ ์ํํ์ง ์์์ ์ฑ๋ฅ ํฅ์
    public ItemFormDto getItemDtl(Long itemId){
        List<ItemImg> itemImgList = itemImgRepository.findByItemIdOrderByIdAsc(itemId);
        //ํด๋น์ํ ์ด๋ฏธ์ง๋ฅผ ์กฐํ / ๋ฑ๋ก ์์ผ๋ก ๊ฐ์ง๊ณ  ์ค๊ธฐ์ํด ์ํ ์ด๋ฏธ์ง ์์ด๋๋ฅผ ์ค๋ฆ์ฐจ์์ผ๋ก ๊ฐ์ง๊ณ ์ด
        List<ItemImgDto> itemImgDtoList = new ArrayList<>();

        for(ItemImg itemImg : itemImgList){ //์กฐํํ ItemImg Entity ๋ฅผ ItemImgDto ๊ฐ์ฒด๋ก ๋ง๋ค์ด์ ๋ฆฌ์คํธ์ ์ถ๊ฐ
            ItemImgDto itemImgDto = ItemImgDto.of(itemImg);
            itemImgDtoList.add(itemImgDto);
        }

        Item item = itemRepository.findById(itemId).orElseThrow(EntityNotFoundException::new);
        //์ํ์ ์์ด๋๋ฅผ ํตํด ์ํ ์ํฐํฐ๋ฅผ ์กฐํ            //์กด์ฌํ์ง ์์ผ๋ฉด ์์ธ ๋ฐ์
        ItemFormDto itemFormDto = ItemFormDto.of(item);
        itemFormDto.setItemImgDtoList(itemImgDtoList);
        return itemFormDto;
    }
 public Long updateItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFielLIst) throws Exception{

        //์ํ์์ 
        Item item = itemRepository.findById(itemFormDto.getId()).orElseThrow(EntityNotFoundException::new);
        item.updateItem(itemFormDto);
        List<Long> itemImgIds = itemFormDto.getItemImgIds();

        for(int i = 0; i < itemImgFielLIst.size(); i++){
            itemImgService.updateItemImg(itemImgIds.get(i),itemImgFielLIst.get(i));
        }
        return item.getId();
    }
```

<br>

## ๐ ์ด๋ฏธ์ง ๋ฑ๋ก ๋ฐ ์์  ๊ธฐ๋ฅ

```java 
 public void saveItemImg(ItemImg itemImg, MultipartFile itemImgFile) throws Exception{
        String oriImgName = itemImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        //ํ์ผ ์๋ก๋
        if(!StringUtils.isEmpty(oriImgName)){
            imgName = fileService.uploadFile(itemImgLocation, oriImgName, itemImgFile.getBytes());
            imgUrl = "/images/item/" + imgName;
        }

        itemImg.updateItemImg(oriImgName, imgName, imgUrl);
        itemImgRepository.save(itemImg);
    }

    public void  updateItemImg(Long itemImgId, MultipartFile itemImgFile) throws Exception{

        if(!itemImgFile.isEmpty()){
            ItemImg savedItemImg = itemImgRepository.findById(itemImgId).orElseThrow(EntityNotFoundException::new);

            //๊ธฐ์กด ์ด๋ฏธ์ง ํ์ผ ์ญ์ 
            if(!StringUtils.isEmpty(savedItemImg.getImgName())) {
                fileService.deleteFile(itemImgLocation + "/" + savedItemImg.getImgName());
            }

            String oriImgName = itemImgFile.getOriginalFilename();
            String imgName = fileService.uploadFile(itemImgLocation, oriImgName, itemImgFile.getBytes());
            //์๋ฐ์ดํธํ ์ํ ์ด๋ฏธ์ง๋ฅผ ํ์ผ์ ์๋ก๋
            String imgUrl = "/images/item/" + imgName;
            savedItemImg.updateItemImg(oriImgName, imgName, imgUrl);
        }

    }
```

### WebMvcConfigure
```java
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${uploadPath}")
    String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                //์น ๋ธ๋ผ์ฐ์ ์ ์๋ ฅํ๋ url ์ /images ๋ก ์์ํ๋ ๊ฒฝ์ฐ uploadPath ์ ์ค์ ํ ๋ค์์ผ๋ก ์ค์ผ๋ก ํ์ผ์ ์ฝ์ด ์ค๋๋ก ์ค์ 
                .addResourceLocations(uploadPath); //๋ก์ปฌ์ปดํจํฐ์ ์ ์ฅ๋ ํ์ผ์ ์ฝ์ด์ฌ ๊ฒฝ๋ก ์ค์ 

    }

}
```


<br>

## ๐ ์ํ ์์ธ์ ๋ณด
![แแกแผแแฎแท แแกแผแแฆแแฅแผแแฉ](https://user-images.githubusercontent.com/110580350/210510373-4be43025-df79-4f0b-86da-50cd799daa59.gif)


#### Controller
```java
 @GetMapping(value = {"/admin/items", "/admin/items/{page}"})
    public String pageable(ItemSearchDto itemSearchDto, @PathVariable("page") Optional<Integer> page, Model model){

        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 3);
        Page<Item> items = itemService.getAdminItemPage(itemSearchDto, pageable);

        model.addAttribute("items",items);
        model.addAttribute("itemSearchDto",itemSearchDto);
        model.addAttribute("maxPage",5);

        //ItemController ํด๋์ค์ ์ํ ๊ด๋ฆฌ ํ๋ฉด ์ด๋ ๋ฐ ์กฐํํ ์ํ ๋ฐ์ดํฐ๋ฅผ ํ๋ฉด์ ์ ๋ฌํ๋ ๋ก์ง์ ๊ตฌํ
        //ํ์ฌ ์ํ ๋ฐ์ดํฐ๊ฐ ๋ง์ด ์๋ ๊ด๊ณ๋ก ํ ํ์ด์ง๋น ์ด 3๊ฐ์ ์ํ๋ง ๋ณด์ฌ์ฃผ๋ก ์ค์ ํจ

        return "item/itemMng";

    }

    @GetMapping("/item/{itemId}")
    public String itemDtl(Model model, @PathVariable("itemId") Long itemId){
        ItemFormDto itemFormDto = itemService.getItemDtl(itemId);
        model.addAttribute("item",itemFormDto);
        return "item/itemDtl";
    }

    @GetMapping("/items") //์ํ ๋ฉ์ธ ํ์ด์ง
    public String main(ItemSearchDto itemSearchDto, Optional<Integer> page, Model model){

        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 6);
        Page<MainItemDto> items = itemService.getMainItemPage(itemSearchDto, pageable);

        model.addAttribute("items",items);
        model.addAttribute("itemSearchDto",itemSearchDto);
        model.addAttribute("maxPage",5);

        return "item/items";
    }
```

#### Service
```java
 @Transactional(readOnly = true) //๊ด๋ฆฌ์๋ง ๋ณด์ด๋ ์ํ ๋ชฉ๋ก ๊ธฐ๋ฅ
    public Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable){
        return itemRepository.getAdminItemPage(itemSearchDto, pageable);
    }
    //ItemService ํด๋์ค์ ์ํ ์กฐํ ์กฐ๊ฑด๊ณผ ํ์ด์ง ์ ๋ณด๋ฅผ ํ๋ผ๋ฏธํฐ๋ก ๋ฐ์์ ์ํ ๋ฐ์ดํฐ๋ฅผ ์กฐํํ๋ getAdminItemPage() ๋ฉ์๋๋ฅผ ์ถ๊ฐ
    //๋ฐ์ดํฐ์ ์์ ์ด ์ผ์ด๋์ง ์์ผ๋ฏ๋ก ์ต์ ํ๋ฅผ ์ํด @Transactional(readOnly = true) ์ด๋ธํ์ด์์ ์ค์ 

    @Transactional(readOnly = true) //์ด์ฉ์์๊ฒ ๋ณด์ด๋ ์ํ ํ์ด์ง
    public Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable){
        return itemRepository.getMainItemPage(itemSearchDto, pageable);
    }
```
<br>

## ๐ ์ํ ์ฃผ๋ฌธ ๊ธฐ๋ฅ
![แแกแผแแฎแท แแฎแแฎแซ แแตแแณแผ](https://user-images.githubusercontent.com/110580350/210510987-f9084231-ca17-408f-802d-e4877837dcc0.gif)

#### Controller
```java
 @PostMapping("/order")
    public @ResponseBody ResponseEntity order(@RequestBody @Valid OrderDto orderDto, BindingResult bindingResult, Principal principal){
        //์ฃผ๋ฌธ ์ ๋ณด๋ฅผ ๋ฐ๋ orderDto ๊ฐ์ฒด์ ๋ฐ์ดํฐ ๋ฐ์ธ๋ฉ ์ ์๋ฌ๊ฐ ์๋ ์ง ๊ฒ์ฌ
        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();

            for(FieldError fieldError : fieldErrors){
                sb.append(fieldError.getDefaultMessage());
            }
            return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        String email = principal.getName();
        //ํ์ฌ ๋ก๊ทธ์ธ ์ ์ ์ ์ ๋ณด๋ฅผ ์ป๊ธฐ์ํด @Controller ์ด๋ธํ์ด์์ด ์ ์ธ๋ ํด๋์ค์์ ๋ฉ์๋ ์ธ์๋ก Principal ๊ฐ์ฒด๋ก ๋๊ฒจ์ค ๊ฒฝ์ฐ ํด๋น ๊ฐ์ฒด์ ์ ๊ทผํ  ์ ์๋ค.
        //Principal ๊ฐ์ฒด์์ ํ์ฌ ๋ก๊ทธ์ธํ ํ๋ฉด์ ์ด๋ฉ์ผ ์ ๋ณด๋ฅผ ์กฐํ
        Long orderId;


        try{
            orderId = orderService.order(orderDto, email);
            //ํ๋ฉด์ผ๋ก ๋ถํฐ ๋์ด์ค๋ ์ฃผ๋ฌธ ์ ๋ณด์ ํ์์ ์ด๋ฉ์ผ ์ ๋ณด๋ฅผ ์ด์ฉํ์ฌ ์ฃผ๋ฌธ ๋ก์ง์ ํธ์ถ
        }catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Long>(orderId, HttpStatus.OK);
        //๊ฒฐ๊ณผ๊ฐ์ผ๋ก ์์ฑ๋ ์ฃผ๋ฌธ ๋ฒํธ์ ์์ฒญ์ด ์ฑ๊ณตํ๋ค๋ Http ์๋ต ์ํ ์ฝ๋๋ฅผ ๋ฐํ

    }
```

#### Service
```java
public Long order(OrderDto orderDto, String email){
        Item item = itemRepository.findById(orderDto.getItemId()).orElseThrow(EntityNotFoundException::new);

        Member member = memberRepository.findByEmail(email);
        //ํ์ฌ ๋ก๊ทธ์ธํ ํ์์ ์ด๋ฉ์ผ ์ ๋ณด๋ฅผ ์ด์ฉํ์ฌ ํ์์ ๋ณด ์กฐํ
        List<OrderItem> orderItemList = new ArrayList<>();
        OrderItem orderItem = OrderItem.createOrderItem(item, orderDto.getCount());
        //์ฃผ๋ฌธํ  ์ํ ์ํฐํฐ์ ์ฃผ๋ฌธ ์๋์ ์ด์ฉํ์ฌ ์ฃผ๋ฌธ ์ํ ์ํฐํฐ๋ฅผ ์์ฑ
        orderItemList.add(orderItem);

        Order order = Order.createOrder(member, orderItemList);
        //ํ์์ ๋ณด์ ์ฃผ๋ฌธํ  ์ํ ๋ฆฌ์คํธ ์ ๋ณด๋ฅผ ์ด์ฉํ์ฌ ์ฃผ๋ฌธ ์ํฐํฐ๋ฅผ ์์ฑ
        orderRepository.save(order);
        return order.getId();
    }
```

<br>

## ๐ ์ฅ๋ฐ๊ตฌ๋ ๊ธฐ๋ฅ
![แแกแผแแกแแฎแแต แแตแแณแผ](https://user-images.githubusercontent.com/110580350/210511420-5c60c224-3ec0-485f-a2d5-acf8b4c2e7f5.gif)


#### Controller
```java
 @PostMapping("/cart")
    public @ResponseBody ResponseEntity order(@RequestBody @Valid CartItemDto cartItemDto, BindingResult bindingResult, Principal principal){

        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            List<FieldError> filedErrors = bindingResult.getFieldErrors();

            for(FieldError fieldError : filedErrors){
                sb.append(fieldError.getDefaultMessage());
            }
            return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
        }
        //ํ์ฌ ๋ก๊ทธ์ธํ ํ์์ ์ด๋ฉ์ผ ์ ๋ณด๋ฅผ ๋ณ์์ ์ ์ฅ
        String email = principal.getName();
        Long cartItemId;

        try{
            cartItemId = cartService.addCart(cartItemDto, email);
            //๊ฐ์ผ๋ก ๋์ด์จ ์ฅ๋ฐ๊ตฌ๋์ ๋ด์ ์ํ ์ ๋ณด์ ํ์ฌ ๋ก๊ทธ์ธํ ํ์์ ์ด๋ฉ์ผ ์ ๋ณด๋ฅผ ์ด์ฉํ์ฌ ์ฅ๋ฐ๊ตฌ๋์ ์ํ์ ๋ด๋ ๋ก์ง์ ํธ์ถ
        }catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Long>(cartItemId, HttpStatus.OK);
        //๊ฒฐ๊ณผ๊ฐ์ผ๋ก ์์ฑ๋ ์ฅ๋ฐ๊ตฌ๋ ์ํ ์์ด๋์ ์์ฒญ์ด ์ฑ๊ณตํ์๋ค๋ Http ์๋ต ์ํ ์ฝ๋ ๋ฐํ
    }
```

#### Service
```java
 public Long addCart(CartItemDto cartItemDto, String email){
        Item item = itemRepository.findById(cartItemDto.getItemId()).orElseThrow(EntityNotFoundException::new);
        //์ฅ๋ฐ๊ตฌ๋์ ๋ด์ Item Entity ์กฐํ
        Member member = memberRepository.findByEmail(email);
        //ํ์ฌ ๋ก๊ทธ์ธํ Member Entity ์กฐํ
        Cart cart = cartRepository.findByMemberId(member.getId());
        //ํ์ฌ ๋ก๊ทธ์ธํ ํ์์ Cart Entity ์กฐํ
        if(cart == null){ //์ํ์ ์ฒ์์ผ๋ก ์ฅ๋ฐ๊ตฌ๋์ ๋ด์ ๊ฒฝ์ฐ ํด๋น ํ์์ Cart Entity ์์ฑ
            cart = Cart.creatCart(member);
            cartRepository.save(cart);
        }
        CartItem savedCartItem = cartItemRepository.findByCartIdAndItemId(cart.getId(), item.getId());
        //ํ์ฌ ์ํ์ด ์ฅ๋ฐ๊ตฌ๋์ ์ด๋ฏธ ๋ค์ด๊ฐ ์๋ ์ง ์กฐํ
        if(savedCartItem != null){
            savedCartItem.addCount(cartItemDto.getCount());
            return savedCartItem.getId();
            //์ฅ๋ฐ๊ตฌ๋์ ์ด๋ฏธ ์๋ ์ํ์ผ ๊ฒฝ์ฐ ๊ธฐ์กด ์๋์ ํ์ฌ ์ฅ๋ฐ๊ตฌ๋์ ๋ด์ ์๋๋งํผ ๋ํจ
        }else{
            CartItem cartItem = CartItem.createCartItem(cart, item, cartItemDto.getCount());
            //Cart Entity, Item Entity, Count ๋ฅผ ์ด์ฉํ์ฌ CartItem Entity ์์ฑ
            cartItemRepository.save(cartItem); //์ฅ๋ฐ๊ตฌ๋ ์ ์ฅ
            return cartItem.getId();
        }
    } 
```

<br>

## ๐ ์ํ ์ทจ์ ๊ธฐ๋ฅ
![แแกแผแแฎแท แแฑแแฉ](https://user-images.githubusercontent.com/110580350/210512144-438c0b2d-dcb3-4b5c-998e-e420f8929399.gif)

#### Controller
```java
@PostMapping("/order/{orderId}/cancel")
    public @ResponseBody ResponseEntity cacelOrder(@PathVariable("orderId") Long orderId, Principal principal){
        if(!orderService.validateOrder(orderId, principal.getName())){
            return new ResponseEntity<String>("์ฃผ๋ฌธ ์ทจ์ ๊ถํ์ด ์์ต๋๋ค.",HttpStatus.FORBIDDEN);
        }
        orderService.cancelOrder(orderId);
        return new ResponseEntity<Long>(orderId,HttpStatus.OK);
    }

    @DeleteMapping("/orderItem/{orderItemId}")
    public @ResponseBody ResponseEntity deleteOrderItem(@PathVariable("orderItemId") Long orderItemId, Principal principal){

        if(!orderService.validateOrder(orderItemId, principal.getName())){
            return new ResponseEntity<String>("์ฃผ๋ฌธ ์ทจ์ ๊ถํ์ด ์์ต๋๋ค.",HttpStatus.FORBIDDEN);
        }

        orderService.deleteOrderItem(orderItemId);
        return new ResponseEntity<Long>(orderItemId, HttpStatus.OK);
    }
```
#### Service
```java
 public void cancelOrder(Long orderId){
        Order order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
        order.cancelOrder();
    } //์ฃผ๋ฌธ ์ทจ์ ์ํ๋ก ๋ณ๊ฒฝํ๋ฉด ๋ณ๊ฒฝ๊ฐ์ง ๊ธฐ๋ฅ์ ์ํด์ ํธ๋ ์ ์์ด ๋๋  ๋ update ์ฟผ๋ฆฌ ์คํ
    
  public void deleteOrderItem(Long orderItemId){ //์ทจ์๋ ์ฃผ๋ฌธ๋ด์ญ ์ญ์ 
      Order order = orderRepository.findById(orderItemId).orElseThrow(EntityNotFoundException::new);
      orderRepository.delete(order);
  }
  
  <div class="ml-3"> <!--์ฃผ๋ฌธ ์ทจ์์ํ์ผ ๊ฒฝ์ฐ ์ฃผ๋ฌธ๋ด์ญ ์ญ์  ๋ฒํผ ์ถ๊ฐ-->
                <th:block th:unless="${order.orderStatus == T(Spring.Project.status.OrderStatus).ORDER}">
                    <button type="button" class="btn btn-outline-danger" th:value="${order.orderId}"
                            th:data-id="${order.orderId}" onclick="deleteOrders(this)">์ฃผ๋ฌธ๋ด์ญ ์ญ์ </button>
                </th:block>
            </div>
```

<br>









