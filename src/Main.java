import Controller.*;
import View.*;
import Model.*;

import javax.swing.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        FileHandling myFile = new FileHandling("First Time");
        HomeScreen hs = new HomeScreen();
        hs.showScreen();
    }
}