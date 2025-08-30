import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

class Information {
    public static void info(Connection con, Scanner sc) {
        String name;
        long phone_no;
        String Address;
        String query = ("INSERT INTO info(name, Phone_No, Address) VALUES (?, ?, ?)");

        System.out.print("Please enter your name: ");
        sc.nextLine();
        name = sc.nextLine();

        System.out.print("Please enter your phone number: ");
        phone_no = sc.nextLong();
        sc.nextLine();

        System.out.print("Please enter your address: ");
        Address = sc.nextLine();

        System.out.println("Your name: " + name);
        System.out.println("Your phone number: " + phone_no);
        System.out.println("Your address: " + Address);

        try {
            try (PreparedStatement p = con.prepareStatement(query)) {
                p.setString(1, name);
                if (phone_no >= 1_000_000_000L && phone_no <= 9_999_999_999L) {
                    p.setLong(2, phone_no);
                } else {
                    System.out.println("Please enter valid number");
                }
                p.setString(3, Address);
                int rowsaffect = p.executeUpdate();
                if (rowsaffect > 0) {
                    System.out.println("DATA SAVED SUCCESSFULLY");
                } else {
                    System.out.println("Something is wrong");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        plasticType(con, sc);
    }

    public static void updateInfo(Connection con, Scanner sc) {
        String name = "";
        long phone_no = 0;
        String Address = "";
        String query = ("INSERT INTO info(name, Phone_No, Address) VALUES (?, ?, ?)");

        System.out.println("What do you want to update: ");
        System.out.println("Enter 1 for name");
        System.out.println("Enter 2 for phone number");
        System.out.println("Enter 3 for address");
        System.out.println("Enter 4 for re-enter details");
        int option = sc.nextInt();
        sc.nextLine();

        if (option == 1) {
            System.out.print("Please enter your name: ");
            name = sc.nextLine();
            System.out.println("Name modified: " + name);
        } else if (option == 2) {
            System.out.print("Please enter your phone number: ");
            phone_no = sc.nextLong();
            sc.nextLine();
            System.out.println("Phone number modified: " + phone_no);
        } else if (option == 3) {
            System.out.print("Please enter your address: ");
            Address = sc.nextLine();
            System.out.println("Address modified: " + Address);
        } else if (option == 4) {
            System.out.print("Please enter your name: ");
            name = sc.nextLine();

            System.out.print("Please enter your phone number: ");
            phone_no = sc.nextLong();
            sc.nextLine();

            System.out.print("Please enter your address: ");
            Address = sc.nextLine();

            System.out.println("Your updated name: " + name);
            System.out.println("Your updated phone number: " + phone_no);
            System.out.println("Your updated address: " + Address);
        } else {
            System.out.println("Please enter a valid option");
            return;
        }

        try {
            try (PreparedStatement p = con.prepareStatement(query)) {
                p.setString(1, name);
                if (phone_no >= 1_000_000_000L && phone_no <= 9_999_999_999L) {
                    p.setLong(2, phone_no);
                } else {
                    System.out.println("Please enter valid number");
                }
                p.setString(3, Address);
                int rowsaffect = p.executeUpdate();
                if (rowsaffect > 0) {
                    System.out.println("DATA SAVED SUCCESSFULLY");
                } else {
                    System.out.println("Something is wrong");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        plasticType(con, sc);
    }

    public static void plasticType(Connection con, Scanner sc) {
        String updateQuery = ("INSERT INTO calculation(plastic_Type,REWARDS,CO2_SAVED,Image)VALUES(?,?,?,?)");

        System.out.println("PET - Water bottles");
        System.out.println("HDPE - Detergent bottles, milk jugs");
        System.out.println("LDPE - Plastic bags, wrappers");
        System.out.println("PP - Food Containers, bottle caps");
        System.out.println("PS - Disposable cups, trays");
        System.out.println("Mixed/Unclassified - Toys, Blister packs, PVC");

        System.out.println("Enter option 1: Polyethylene Terephthalate (PET)");
        System.out.println("Enter option 2: High-Density Polyethylene (HDPE)");
        System.out.println("Enter option 3: Low-Density Polyethylene (LDPE)");
        System.out.println("Enter option 4: Polypropylene (PP)");
        System.out.println("Enter option 5: Polystyrene (PS)");
        System.out.println("Enter option 6: Mixed/Unclassified Plastics");

        int option = sc.nextInt();
        float sumWeight = 0;
        float weight = 0;
        float reward = 0;
        float rate = 0;
        float sumReward = 0;
        float Co2_Saved;
        float totalco2_Saved = 0;
        String final_String;
        String f_String = "";
        String image_Path = "E:\\image1.jpg";

        while (true) {
            switch (option) {
                case 1:
                    weight = calculateWeight(sc, 20);
                    rate = calculateReward(sc, 20);
                    reward = reward(weight, rate);
                    Co2_Saved = 1.5f;
                    final_String = "PET";
                    break;
                case 2:
                    weight = calculateWeight(sc, 50);
                    rate = calculateReward(sc, 50);
                    reward = reward(weight, rate);
                    Co2_Saved = 1.7f;
                    final_String = "HDPE";
                    break;
                case 3:
                    weight = calculateWeight(sc, 8);
                    rate = calculateReward(sc, 8);
                    reward = reward(weight, rate);
                    Co2_Saved = 1.0f;
                    final_String = "LDPE";
                    break;
                case 4:
                    weight = calculateWeight(sc, 10);
                    rate = calculateReward(sc, 10);
                    reward = reward(weight, rate);
                    Co2_Saved = 1.3f;
                    final_String = "PP";
                    break;
                case 5:
                    weight = calculateWeight(sc, 5);
                    rate = calculateReward(sc, 5);
                    reward = reward(weight, rate);
                    Co2_Saved = 0.8f;
                    final_String = "PS";
                    break;
                case 6:
                    System.out.println("Enter weight: ");
                    int w = sc.nextInt();
                    weight = calculateWeight(sc, w);
                    rate = calculateReward(sc, w);
                    reward = reward(weight, rate);
                    Random r = new Random();
                    Co2_Saved = 0.5f + r.nextFloat() * (0.9f - 0.5f);
                    final_String = "Other";
                    break;
                default:
                    System.out.println("Invalid option.");
                    return;
            }

            sumWeight += weight;
            sumReward += reward;
            totalco2_Saved += Co2_Saved;
            f_String += final_String;

            System.out.println("Do you want to choose again? Enter Y for yes:");
            sc.nextLine();
            String choose = sc.nextLine();
            if (!choose.equalsIgnoreCase("y")) break;

            System.out.println("Enter new option:");
            option = sc.nextInt();
        }

        System.out.println("Weight is: " + sumWeight);
        System.out.println("Your reward: " + sumReward);
        System.out.println("CO2 SAVED: " + totalco2_Saved);

        try {
            try (PreparedStatement ps = con.prepareStatement(updateQuery)) {
                ps.setString(1, f_String);
                ps.setFloat(2, sumReward);
                ps.setFloat(3, totalco2_Saved);
                FileInputStream f = new FileInputStream(image_Path);
                byte[] image = new byte[f.available()];
                f.read(image);
                ps.setBytes(4, image);
                int affectrows = ps.executeUpdate();
                if (affectrows > 0) {
                    System.out.println("Data saved successfully");
                } else {
                    System.out.println("Something is wrong");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static float calculateWeight(Scanner sc, int peritemweight) {
        System.out.print("Please enter the number of items: ");
        int Numberofitems = sc.nextInt();
        float weight = (Numberofitems * peritemweight) / 1000f;
        System.out.println("Number of items: " + Numberofitems);
        System.out.println("Weight: " + weight);
        return weight;
    }

    public static float calculateReward(Scanner sc, int weight) {
        float rate;
        Random r = new Random();
        if (weight == 20) {
            rate = r.nextInt(6) + 15;
        } else if (weight == 50) {
            rate = r.nextInt(5) + 18;
        } else if (weight == 8) {
            rate = r.nextInt(5) + 8;
        } else if (weight == 10) {
            rate = r.nextInt(5) + 10;
        } else if (weight == 5) {
            rate = r.nextInt(5) + 6;
        } else {
            rate = r.nextInt(4) + 3;
        }
        return rate;
    }

    public static float reward(float weight, float rate) {
        return weight * rate;
    }
}

public class Recycalytics extends Information {
    private static final String url = "jdbc:mysql://localhost:3306/recycle";
    private static final String user = "root";
    private static final String password = "Password007";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println();
                System.out.println("WELCOME TO OUR RECYCLE CENTRE");
                System.out.println("Please select 1: add info");
                System.out.println("Please select 2: update info");

                int option = sc.nextInt();
                switch (option) {
                    case 1:
                        info(con, sc);
                        break;
                    case 2:
                        updateInfo(con, sc);
                        break;
                    default:
                        System.out.println("ENTER A VALID OPTION");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}


