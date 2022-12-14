package Spring.Project.repository.item;

import Spring.Project.dto.item.ItemSearchDto;
import Spring.Project.dto.item.MainItemDto;
import Spring.Project.entity.item.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {


    Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);

    //상품 조회 조건을 담고 있는 itemSearchDto 객체의 페이징 정보를 담고 있는 pageable 객체를 파라미터로 받는
    //getAdminItemPage 를 정의 반환 데이터로 Page<Item> 객체를 반환

    Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable);


}
