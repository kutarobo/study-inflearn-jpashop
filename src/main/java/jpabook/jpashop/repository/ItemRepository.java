package jpabook.jpashop.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
	private final EntityManager em;

	public void save(Item item) {
		if (item.getId() == null) {
			em.persist(item);
			return;
		}
		// 실무에서는 가급적 머지를 사용하지말고 변경감지를 사용하여 엔티티를 관리하자
		// 머지를 사용하면 누락되는 값이 null 로 될 위험이있다. 변경감지는 변경된것만 변경되고 나머지는 기존값을 유지한다.
		em.merge(item);
	}

	public Item findOne(Long id) {
		return em.find(Item.class, id);
	}

	public List<Item> findAll() {
		return em.createQuery("select i from Item i", Item.class)
			.getResultList();
	}
}
