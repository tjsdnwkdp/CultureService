package com.culture.CultureService.controller;

import com.culture.CultureService.dto.ShowDto;
import com.culture.CultureService.dto.ShowSearchDto;
import com.culture.CultureService.entity.ShowEntity;
import com.culture.CultureService.service.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ShowController {
    private final ShowService showService;

    @GetMapping(value = {"/show", "show/{page}"})
    public String showList(ShowSearchDto showSearchDto, @PathVariable("page") Optional<Integer> page, Model model) {
        //page.isPresent() 값 있으면 page.get(), 없으면 0 반환. 페이지 당 사이즈 20개
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 20);

        Page<ShowEntity> shows = showService.getShowListPage(showSearchDto, pageable);
        model.addAttribute("shows", shows);
        model.addAttribute("showSearchDto", showSearchDto);
        model.addAttribute("maxPage", 20);
        return "show/showList";


    }
}