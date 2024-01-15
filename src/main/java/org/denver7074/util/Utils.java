package org.denver7074.util;

import com.google.common.collect.ImmutableMap;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LongStringConverter;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.denver7074.domain.Company;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@UtilityClass
public class Utils {

    public static final Map<Class<?>, Function<String, Object>> converter = ImmutableMap.of(
            String.class, Object::toString,
            Long.class, NumberUtils::createLong,
            Boolean.class, Boolean::parseBoolean,
            Integer.class, NumberUtils::createInteger
    );

    @SneakyThrows
    public <T> Boolean anyNullTextField(T obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field f : fields) {
            if (Objects.equals(f.getType().getSimpleName(), "TextField")) {
                f.setAccessible(true);
                String text = ((TextField) f.get(obj)).getText();
                if (ObjectUtils.isEmpty(text)) return true;
            }
        }
        return false;
    }

    @SneakyThrows
    public <T> void setTextField(T obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field f : fields) {
            if (Objects.equals(f.getType().getSimpleName(), "TextField")) {
                f.setAccessible(true);
                ((TextField) f.get(obj)).setText("");
                f.setAccessible(false);
            }
        }
    }

    public <T> List<TableColumn<T, String>> createTableColumn(Class<T> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        List<TableColumn<T, String>> list = new ArrayList<>(fields.length);
        for (Field f : fields) {
            TableColumn<T, String> column = new TableColumn<>(f.getAnnotation(ColumnName.class).name());
            column.setCellValueFactory(new PropertyValueFactory<>(f.getName()));
            list.add(column);
        }
        return list;
    }

}
