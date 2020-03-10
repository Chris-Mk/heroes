package com.mkolongo.heros.service;

import com.mkolongo.heros.domain.entities.Hero;
import com.mkolongo.heros.domain.entities.HeroItems;
import com.mkolongo.heros.domain.entities.Item;
import com.mkolongo.heros.domain.models.binding.ItemCreateModel;
import com.mkolongo.heros.domain.models.view.ItemViewModel;
import com.mkolongo.heros.repository.HeroItemsRepository;
import com.mkolongo.heros.repository.HeroRepository;
import com.mkolongo.heros.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final HeroItemsRepository heroItemsRepository;
    private final HeroRepository heroRepository;
    private final ItemRepository itemRepository;
    private final ModelMapper mapper;

    @Override
    public void create(ItemCreateModel itemCreateModel) {
        Item item = mapper.map(itemCreateModel, Item.class);
        itemRepository.save(item);
    }

    @Override
    public List<ItemViewModel> getAllItems() {
        return mapper.map(itemRepository.findAll(), new TypeToken<List<ItemViewModel>>() {
        }.getType());
    }

    @Override
    public void buyItem(String id, Hero hero) {
        Item item = itemRepository.getOne(id);

        HeroItems heroItems = new HeroItems();
        heroItems.setItem(item);
        heroItems.setHero(hero);

        hero.setAttack(item.getAttack() + hero.getAttack());
        hero.setDefence(item.getDefence() + hero.getDefence());
        hero.setStamina(item.getStamina() + hero.getStamina());
        hero.setStrength(item.getStrength() + hero.getStrength());

        int sum = hero.getAttack() + hero.getDefence() +
                  hero.getStrength() + hero.getStamina();

        if (sum > 20) {
            hero.setLevel(hero.getLevel() + 1);
        }

        heroRepository.save(hero);
        heroItemsRepository.save(heroItems);
    }
}
