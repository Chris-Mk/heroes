package com.mkolongo.heros.service;

import com.mkolongo.heros.domain.entities.Hero;
import com.mkolongo.heros.domain.models.binding.ItemCreateModel;
import com.mkolongo.heros.domain.models.view.ItemViewModel;

import java.util.List;

public interface ItemService {

    void create(ItemCreateModel itemCreateModel);

    List<ItemViewModel> getAllItems();

    void buyItem(String id, Hero hero);
}
