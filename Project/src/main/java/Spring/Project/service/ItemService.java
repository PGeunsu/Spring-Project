package Spring.Project.service;

import Spring.Project.dto.item.ItemFormDto;
import Spring.Project.dto.item.ItemImgDto;
import Spring.Project.dto.item.ItemSearchDto;
import Spring.Project.dto.item.MainItemDto;
import Spring.Project.entity.item.Item;
import Spring.Project.entity.item.ItemImg;
import Spring.Project.repository.item.ItemImgRepository;
import Spring.Project.repository.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemImgRepository itemImgRepository;
    private final ItemRepository itemRepository;
    private final ItemImgService itemImgService;

    public Long saveItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws Exception{
        //상품 등록
        Item item = itemFormDto.createItem();
        itemRepository.save(item);

        //이미지 등록
        for(int i=0;i<itemImgFileList.size();i++){
            ItemImg itemImg = new ItemImg();
            itemImg.setItem(item);

            if(i == 0)
                itemImg.setRepImgYn("Y"); //첫번 쨰 이미지를 Y를 주고 대표이미지 설정
            else
                itemImg.setRepImgYn("N");

            itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
            //상품 이미지를 저장
        }

        return item.getId();
    }
    @Transactional(readOnly = true) //트랜젝션을 읽기 전용으로 설정
    //Jpa 가 더티체킹(변경 감지)을 수행하지 않아서 성능 향상
    public ItemFormDto getItemDtl(Long itemId){
        List<ItemImg> itemImgList = itemImgRepository.findByItemIdOrderByIdAsc(itemId);
        //해당상품 이미지를 조회 / 등록 순으로 가지고 오기위해 상품 이미지 아이디를 오름차순으로 가지고옴
        List<ItemImgDto> itemImgDtoList = new ArrayList<>();

        for(ItemImg itemImg : itemImgList){ //조회한 ItemImg Entity 를 ItemImgDto 객체로 만들어서 리스트에 추가
            ItemImgDto itemImgDto = ItemImgDto.of(itemImg);
            itemImgDtoList.add(itemImgDto);
        }

        Item item = itemRepository.findById(itemId).orElseThrow(EntityNotFoundException::new);
        //상품에 아이디를 통해 상품 엔티티를 조회            //존재하지 않으면 에외 발생
        ItemFormDto itemFormDto = ItemFormDto.of(item);
        itemFormDto.setItemImgDtoList(itemImgDtoList);
        return itemFormDto;
    }

    public Long updateItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFielLIst) throws Exception{

        //상품수정
        Item item = itemRepository.findById(itemFormDto.getId()).orElseThrow(EntityNotFoundException::new);
        item.updateItem(itemFormDto);
        List<Long> itemImgIds = itemFormDto.getItemImgIds();

        for(int i = 0; i < itemImgFielLIst.size(); i++){
            itemImgService.updateItemImg(itemImgIds.get(i),itemImgFielLIst.get(i));
        }
        return item.getId();
    }

    @Transactional(readOnly = true)
    public Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable){
        return itemRepository.getAdminItemPage(itemSearchDto, pageable);
    }
    //ItemService 클래스에 상품 조회 조건과 페이지 정보를 파라미터로 받아서 상품 데이터를 조회하는 getAdminItemPage() 메소드를 추가
    //데이터의 수정이 일어나지 않으므로 최적화를 위해 @Transactional(readOnly = true) 어노테이션을 설정

    @Transactional(readOnly = true)
    public Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable){
        return itemRepository.getMainItemPage(itemSearchDto, pageable);
    }






}
