package jpabook.jpashop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

	private final ItemRepository itemRepository;

	@Transactional(readOnly = false)
	public void saveItem(Item item) {
		itemRepository.save(item);
	}

	@Transactional
	// public void updateItem(Long itemId, Book param) { 이렇게 북통째로 넘기는것 보다 필요한거 풀어서 넘기
	public void updateItem(Long itemId, String name, int price, int stockQuantity) {
		Item findItem = itemRepository.findOne(itemId);
		findItem.setName(name);
		findItem.setPrice(price);
		findItem.setStockQuantity(stockQuantity);
		// 이메소드가 끝나면 트랜잭션 어노테이션으로 인해 커밋과 플러시가 되어서 데이터가 변경됨.(변경감지 기능)
		// 위의 코드 처럼 set 을 남발하지말고 의미있는 메서드로 따로 빼서 변경할것 만 넘겨서 엔티티를 관리해야한다.
		// ex findItem.changePrice(이런식)으로 뽑아서
	}

	public List<Item> findItems() {
		return itemRepository.findAll();
	}

	public Item findOne(Long itemId) {
		return itemRepository.findOne(itemId);
	}
}
