package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Item itemA = new Item("itemA", 10000, 100);

        //when
        Item savedItem = itemRepository.save(itemA);

        //then
        Item findItem = itemRepository.findById(itemA.getId());
        assertThat(savedItem).isEqualTo(findItem);

    }

    @Test
    void findAll() {
        //given
        Item itemA = new Item("itemA", 10000, 100);
        itemRepository.save(itemA);
        Item itemB = new Item("itemB", 10000, 100);
        itemRepository.save(itemB);
        Item itemC = new Item("itemC", 10000, 100);
        itemRepository.save(itemC);

        //when
        List<Item> findAllItem = itemRepository.findAll();

        //then
        System.out.println("findAllItem = " + findAllItem);
        assertThat(3).isEqualTo(findAllItem.size());
        assertThat(findAllItem).contains(itemA, itemB, itemC);
    }

    @Test
    void updateItem() {
        //given
        Item itemA = new Item("itemA", 10000, 100);

        itemRepository.save(itemA);

        //when
        Item updateParam = new Item("modifiedItemA", 20000, 300);
        itemRepository.update(itemA.getId(), updateParam);

        //then
        Item findItem = itemRepository.findById(itemA.getId());
        assertThat(itemA.getItemName()).isEqualTo(findItem.getItemName());
        assertThat(itemA.getPrice()).isEqualTo(20000);
        assertThat(itemA.getQuantity()).isEqualTo(300);
    }
}