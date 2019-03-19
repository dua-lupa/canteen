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

    public static final String CANTEEN_IMAGE_PATH = "/canteen-images/";

    @Autowired
    private MongoTemplate mongoTemplate;

//    @Autowired
//    private ResourceLoader resourceLoader;

    @Override
    public void run(String... args) throws Exception {
        log.info("Filling initial catalog");

        Schedule fullWeek = Schedule.fullWeek();

        Canteen iuCanteen = Canteen.builder()
                .name("Столовая ИУ. Элитная")
                .location("ГЗ, 3 этаж")
                .schedule(fullWeek)
                .imageUrl(CANTEEN_IMAGE_PATH + "canteen2.jpg")
                .build();
        Canteen mainGZCanteen = Canteen.builder()
                .name("ГЗ Главная")
                .location("ГЗ, слева от красного уголка")
                .schedule(fullWeek)
                .imageUrl(CANTEEN_IMAGE_PATH + "canteen1.jpg")
                .build();
        Canteen ulkCanteen = Canteen.builder()
                .name("УЛК Главная (для олдов)")
                .location("УЛК, 2 этаж, там недалеко")
                .schedule(fullWeek)
                .imageUrl(CANTEEN_IMAGE_PATH + "canteen3.jpg")
                .build();

        Dish soup1 = Dish.builder()
                .name("Суп гороховый с картофелем и конечностями")
                .price(BigDecimal.valueOf(50))
                .weights(asList(
                        new Weight(25, GRAM),
                        new Weight(250, GRAM)))
                .categories(singletonList(SOUP))
                .availableAt(asList(iuCanteen, mainGZCanteen, ulkCanteen))
                .imageUrl(CANTEEN_IMAGE_PATH + "soup1.jpg")
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
                .imageUrl(CANTEEN_IMAGE_PATH + "drink1.jpg")
                .build();

        Dish makarony = Dish.builder()
                .name("Макароны отварные с маслом")
                .price(BigDecimal.valueOf(15))
                .weights(Collections.singletonList(new Weight(150, GRAM)))
                .categories(singletonList(Category.PORRIDGE))
                .availableAt(asList(iuCanteen, mainGZCanteen))
                .imageUrl(CANTEEN_IMAGE_PATH + "soup1.jpg")
                .build();

        Dish pizza = Dish.builder()
                .name("Пицца-мини")
                .price(BigDecimal.valueOf(99))
                .weights(Collections.singletonList(new Weight(350, GRAM)))
                .categories(singletonList(Category.PORRIDGE))
                .availableAt(Collections.singletonList(mainGZCanteen))
                .imageUrl(CANTEEN_IMAGE_PATH + "pizza1.jpg")
                .build();

        this.mongoTemplate.save(soup1);
        this.mongoTemplate.save(teaWithSugar);
        this.mongoTemplate.save(makarony);
        this.mongoTemplate.save(pizza);

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
