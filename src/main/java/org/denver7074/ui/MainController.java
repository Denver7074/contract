package org.denver7074.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.ObjectUtils;
import org.denver7074.domain.Company;
import org.denver7074.service.CrudService;
import org.denver7074.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE)
@SuppressWarnings("SpringJavaAutowiringInspection")
public class MainController {

    // Инъекции Spring
    @Autowired
    CrudService crudService;

    @FXML
    TableView<Company> table;
    @FXML
    TableView<Company> tableService;
    @FXML
    TextFlow warning;
    @FXML
    TextField nameCompany;
    @FXML
    TextField legalAddress;
    @FXML
    TextField actualAddress;
    @FXML
    TextField inn;
    @FXML
    TextField kpp;
    @FXML
    TextField bank;
    @FXML
    TextField correspondentAccount;
    @FXML
    TextField paymentAccount;
    @FXML
    TextField bic;
    @FXML
    TextField ogrn;
    @FXML
    TextField email;
    @FXML
    TextField phone;
    @FXML
    TextField fio;
    @FXML
    TextField post;
    ObservableList<Company> data;

    @FXML
    public void initialize() {
        inn.textProperty().addListener((observable, oldValue, newValue) -> {
            if (ObjectUtils.isEmpty(newValue)) return;
            List<Company> list = filter(newValue);
            if (ObjectUtils.isNotEmpty(list)) {
                ObservableList<Company> newCompany = FXCollections.observableArrayList(list);
                table.setItems(newCompany);
            } else {
                table.setItems(data);
            }
            warning.getChildren().clear();
        });

        TableView.TableViewSelectionModel<Company> selectionModel = table.getSelectionModel();
        selectionModel.selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);
        });

    }

    @SuppressWarnings("unchecked")
    @PostConstruct
    public void init() {
        List<Company> companies = crudService.findAll(Company.class);
        data = FXCollections.observableArrayList(companies);

        List<TableColumn<Company, String>> tableColumn = Utils.createTableColumn(Company.class);

        table.getColumns().addAll(tableColumn);

        table.setItems(data);
    }

    @FXML
    public void addCompany() {
        Text text = new Text();
        if (Utils.anyNullTextField(this)) {
            text.setText("Не все поля заполнены");
            warning.getChildren().add(text);
            return;
        }
//        List<Company> filter = filter(inn.getText());
//        if (ObjectUtils.isEmpty(filter)) return;

        Company company = Company.builder()
                .nameCompany(nameCompany.getText())
                .legalAddress(legalAddress.getText())
                .actualAddress(actualAddress.getText())
                .inn(Integer.valueOf(inn.getText()))
                .kpp(Integer.valueOf(kpp.getText()))
                .bank(bank.getText())
                .correspondentAccount(correspondentAccount.getText())
                .paymentAccount(paymentAccount.getText())
                .bic(Integer.valueOf(bic.getText()))
                .ogrn(Long.valueOf(ogrn.getText()))
                .email(email.getText())
                .phone(phone.getText())
                .fio(fio.getText())
                .post(post.getText())
                .build();
        crudService.save(company);
        data.add(company);

        text.setText("Создана новая запись");
        warning.getChildren().add(text);
        // чистим поля
        Utils.setTextField(this);
    }

    @FXML
    public void createDocument() {

    }

    private List<Company> filter(String inn) {
        return data
                .stream()
                .filter(d -> Objects.equals(d.getInn(), Integer.valueOf(inn)))
                .collect(Collectors.toList());
    }

}
