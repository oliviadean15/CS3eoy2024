import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import java.io.IOException;
import java.security.GeneralSecurityException;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.image.*;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.text.Font;
import javafx.scene.layout.RowConstraints;

public class Visualizer extends Application {
    GridPane textGrid;
    public static void main(String... args){
        Application.launch(Login.class, args);
    }
    public void start(Stage primaryStage) throws IOException, GeneralSecurityException{
        List<List<Object>> values = SheetAccessor.getSheetData();
        System.out.println(values.isEmpty()?"no data found":"");

        List<Student> students = new ArrayList<Student>();
        for (List<Object> row : values) {
            students.add(new Student("" + row.get(0), "" + row.get(1), Integer.parseInt(""+row.get(2)), "", Integer.parseInt(""+row.get(3))));
        }

        Label title = new Label("NHS DATA ORGANIZATION AND VISUALIZATION");
        title.setFont(new Font("Times New Roman",40));
        title.setAlignment(Pos.CENTER);
        File imageFile = new File("C:\\Users\\olivi\\compsci\\CS3 2023-2024\\EOY\\ferrell.png");
        FileInputStream fileInputStream = new FileInputStream(imageFile);
        Image image = new Image(fileInputStream);
        ImageView ferrell = new ImageView(image);
        ferrell.setPreserveRatio(true);
        ferrell.setFitHeight(500);

        Button sortFN = new Button("FIRST NAME");
        sortFN.setMinHeight(50);
        sortFN.setMinWidth(237);
        sortFN.setStyle("-fx-font-size:20");
        Button sortLN = new Button("LAST NAME");
        sortLN.setMinHeight(50);
        sortLN.setMinWidth(237);
        sortLN.setStyle("-fx-font-size:20");
        Button sortID = new Button("ID NUMBER");
        sortID.setMinHeight(50);
        sortID.setMinWidth(237);
        sortID.setStyle("-fx-font-size:20");
        Button sortPGL = new Button("↑ POINTS");
        sortPGL.setMinHeight(50);
        sortPGL.setMinWidth(237);
        sortPGL.setStyle("-fx-font-size:20");
        Button sortPLG = new Button("↓ POINTS");
        sortPLG.setMinHeight(50);
        sortPLG.setMinWidth(237);
        sortPLG.setStyle("-fx-font-size:20");

        textGrid = new GridPane();

        ColumnConstraints col = new ColumnConstraints(237);
        col.setHalignment(HPos.CENTER);

        textGrid.getRowConstraints().add(new RowConstraints(40));
        textGrid.getColumnConstraints().add(col);
        textGrid.getColumnConstraints().add(col);
        textGrid.getColumnConstraints().add(col);
        textGrid.getColumnConstraints().add(col);
        textGrid.getColumnConstraints().add(col);

        textGrid = gridInputter(students);

        Label contact1 = new Label("Contact Information for Olivia Dean: ");
        contact1.setFont(new Font("Times New Roman",20));
        Label contact2 = new Label("Email Address: oliviamdean06@gmail.com");
        contact2.setFont(new Font("Times New Roman",20));
        Label contact3 = new Label("GitHub Username: @oliviadean15");
        contact3.setFont(new Font("Times New Roman",20));

        HBox titleFrame = new HBox(title);
        titleFrame.setAlignment(Pos.CENTER);
        HBox buttons = new HBox(sortFN,sortLN,sortID,sortPGL,sortPLG);
        HBox contact = new HBox(contact1,contact2,contact3);
        contact.setAlignment(Pos.CENTER);
        contact.setSpacing(100);

        BorderPane base = new BorderPane();
        BorderPane borderPane = new BorderPane();
        ScrollPane scrollPane = new ScrollPane();

        scrollPane.setContent(textGrid);

        borderPane.setPrefSize(1000,900);
        borderPane.setTop(buttons);
        borderPane.setCenter(scrollPane);
        borderPane.setBottom(contact);

        base.setTop(titleFrame);
        base.setLeft(ferrell);
        base.setCenter(borderPane);

        VBox root = new VBox(10);
        root.getChildren().add(base);
        Scene scene = new Scene(root,1500,900);
        primaryStage.setTitle("olivia app woo");
        primaryStage.setScene(scene);
        primaryStage.show();

        sortFN.setOnAction(value -> { sortByFirst(students); textGrid = gridInputter(students);});
        sortLN.setOnAction(value -> { sortByLast(students); textGrid = gridInputter(students);});
        sortID.setOnAction(value -> { sortByID(students); textGrid = gridInputter(students);});
        sortPGL.setOnAction(value -> { sortByPointsGL(students); textGrid = gridInputter(students);});
        sortPLG.setOnAction(value -> { sortByPointsLG(students); textGrid = gridInputter(students);});
    }
    public GridPane gridInputter(List<Student> students){
        textGrid.getChildren().clear();

        textGrid.add(new Label("FIRST NAME"),0,0);
        textGrid.add(new Label("LAST NAME"),1,0);
        textGrid.add(new Label("ID NUMBER"),2,0);
        textGrid.add(new Label("HOURS"),3,0);
        textGrid.add(new Label("POINTS"),4,0);
        for(int i = 0, j = 1; i < students.size(); i++, j++){
            textGrid.getRowConstraints().add(new RowConstraints(40));
            textGrid.add(new Label(students.get(i).getFirst()),0,j);
            textGrid.add(new Label(students.get(i).getLast()),1,j);
            textGrid.add(new Label(""+students.get(i).getUserID()),2,j);
            textGrid.add(new Label(""+students.get(i).getHours()),3,j);
            textGrid.add(new Label(""+students.get(i).getPoints()),4,j);
        }
        textGrid.setGridLinesVisible(true);
        return textGrid;
    }
    public static List<Student> sortByLast(List<Student> students) {
        SortingUtil.quickSort(students, 0, students.size() - 1, Comparator.comparing(Student::getLast));
        return students;
    }
    public static List<Student> sortByFirst(List<Student> students) {
        SortingUtil.quickSort(students, 0, students.size() - 1, Comparator.comparing(Student::getFirst));
        return students;
    }
    public static List<Student> sortByID(List<Student> students) {
        SortingUtil.quickSort(students, 0, students.size() - 1, Comparator.comparingInt(Student::getUserID));
        return students;
    }
    public static List<Student> sortByPointsLG(List<Student> students) {
        SortingUtil.quickSort(students, 0, students.size() - 1, Comparator.comparingInt(Student::getPoints));
        return students;
    }
    public static List<Student> sortByPointsGL(List<Student> students) {
        SortingUtil.quickSort(students, 0, students.size() - 1, (s1, s2) -> Integer.compare(s2.getPoints(), s1.getPoints()));
        return students;
    }
}
