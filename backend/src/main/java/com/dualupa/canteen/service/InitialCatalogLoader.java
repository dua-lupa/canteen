package com.dualupa.canteen.service;

import com.dualupa.canteen.dao.canteen.Canteen;
import com.dualupa.canteen.dao.canteen.Schedule;
import com.dualupa.canteen.dao.dish.Category;
import com.dualupa.canteen.dao.dish.Dish;
import com.dualupa.canteen.dao.dish.Weight;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;

import static com.dualupa.canteen.dao.dish.Category.DRINK;
import static com.dualupa.canteen.dao.dish.Category.SOUP;
import static com.dualupa.canteen.dao.dish.Weight.Unit.GRAM;
import static com.dualupa.canteen.dao.dish.Weight.Unit.MILLILITER;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

/**
 * @author avbelyaev
 */
@Component
@Slf4j
public class InitialCatalogLoader implements CommandLineRunner {


    @Autowired
    private MongoTemplate mongoTemplate;

//    @Autowired
//    private ResourceLoader resourceLoader;

    @Override
    public void run(String... args) throws Exception {
        log.info("Filling initial catalog");

        Schedule fullWeek = Schedule.fullWeek();

        Canteen iuCanteen = new Canteen("Столовая ИУ", "ГЗ, 3 этаж", fullWeek);
        Canteen mainGZCanteen = new Canteen("ГЗ Главная", "ГЗ, слева", fullWeek);
        Canteen ulkCanteen = new Canteen("УЛК Главаня", "УЛК, 2 этаж", fullWeek);

        Dish soup1 = Dish.builder()
                .name("Суп гороховый с картофелем и конечностями")
                .price(BigDecimal.valueOf(50))
                .weights(asList(
                        new Weight(25, GRAM),
                        new Weight(250, GRAM)))
                .categories(singletonList(SOUP))
                .nutrition(Dish.Nutrition.builder()
                        .calories(250)
                        .carbohydrates(33)
                        .fats(60.5f)
                        .proteins(0.5f)
                        .build())
                .availableAt(asList(iuCanteen, mainGZCanteen, ulkCanteen))
                .imageUrl("soup1.jpg")
                .build();

        Dish teaWithSugar = Dish.builder()
                .name("Чай с сахаром")
                .price(BigDecimal.valueOf(15))
                .weights(asList(
                        new Weight(200, MILLILITER),
                        new Weight(16, GRAM)
                ))
                .categories(singletonList(DRINK))
                .availableAt(asList(iuCanteen, mainGZCanteen, ulkCanteen))
                .imageUrl("drink1.jpg")
                .build();

        Dish makarony = Dish.builder()
                .name("Макароны отварные с маслом")
                .price(BigDecimal.valueOf(15))
                .weights(Collections.singletonList(new Weight(150, GRAM)))
                .categories(singletonList(Category.PORRIDGE))
                .nutrition(Dish.Nutrition.builder()
                        .calories(150)
                        .calories(15)
                        .fats(50)
                        .proteins(0)
                        .build())
                .availableAt(asList(iuCanteen, mainGZCanteen))
                .imageUrl("soup1.jpg")
                .build();

        this.mongoTemplate.save(soup1);
        this.mongoTemplate.save(teaWithSugar);
        this.mongoTemplate.save(makarony);

        long count = this.mongoTemplate.count(new Query(), Dish.class);
        log.info("Catalog has been filled with {} dishes", count);
    }

//    private String fileToBase64(String filePath) {
//        try {
//            Resource resource = this.resourceLoader.getResource(filePath);
//            byte[] bdata = FileCopyUtils.copyToByteArray(resource.getInputStream());
//            return new String(bdata, StandardCharsets.UTF_8);
//
//        } catch (IOException e) {
//            log.error("Could not convert file {} to base64", filePath, e);
//            return null;
//        }
//    }
}