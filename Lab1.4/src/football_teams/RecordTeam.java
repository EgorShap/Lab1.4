package football_teams;
import java.util.Scanner;

public class RecordTeam {
    //  Methods for subtasks to work with class objects

    //  Enter the football teams array
    public static FootballTeam[] setFootballTeamArray(int num) {

        Scanner scan = new Scanner(System.in, "cp1251");
        FootballTeam team[] = new FootballTeam[num];
        System.out.println("Enter the info about football team =>");

        for (int i = 0; i < team.length; i++) {
            team[i] = new FootballTeam();

            System.out.print("Name of the " + (i + 1) + " team =>");
            team[i].name = scan.nextLine();

            System.out.print("Country of the " + (i + 1) + " team =>");
            team[i].country = scan.nextLine();

            System.out.print("Place in league of the " + (i + 1) + " team =>");
            team[i].league_place = scan.nextInt();

            System.out.print("Number of wins of the " + (i + 1) + " team =>");
            team[i].win_num = scan.nextInt();

            scan.nextLine();  // buffer clearing
        }
        return team;
    }

    //  Output the array
    public static void showArray(FootballTeam[] team) {
        for (int i = 0; i < team.length; i++) {
            System.out.println("" + team[i].name + "\t" + team[i].country + "\t" + team[i].league_place + "\t" + team[i].win_num);
        }
    }

    //  Output info about 1 team
    public static void showTeam(FootballTeam team_show) {
        System.out.println("" + team_show.name + "\t" + team_show.country + "\t" + team_show.league_place + "\t" + team_show.win_num);
    }

    //  Arranging the array by descending places in league
    public static void sortLeaguePlace(FootballTeam[] team_show) {
        for (int i = 0; i < team_show.length - 1; i++) {
            for (int j = 0; j < team_show.length - 1 - i; j++) {
                if (team_show[j].league_place > team_show[j + 1].league_place) {
                    FootballTeam temp = team_show[j];  //temp - temporary variable to sort
                    team_show[j] = team_show[j + 1];
                    team_show[j + 1] = temp;
                }
            }
        }
    }

    //  Average number of wins
    public static double avgWins(FootballTeam[] team) {
        double total_wins = 0;  // the total number of wins
        for (int i = 0; i < team.length; i++) {
            total_wins += team[i].win_num;
        }
        double average_wins = total_wins/team.length;  // the average number of wins
        return average_wins;
    }

    // Teams with number of wins more than average
    public static FootballTeam[] MoreThanAvg(FootballTeam team[]) {
        double avg = avgWins(team);
        int num_more_avg = 0;  // the number of teams
        for (int i = 0; i < team.length; i++) {
            if (team[i].win_num > avg) {
                ++num_more_avg;
            }
        }
        if (num_more_avg != 0) {
            FootballTeam[] team_more = new FootballTeam[num_more_avg];
            int n = -1;
            for (int i = 0; i < team.length; i++) {
                if (team[i].win_num > avg) {
                    team_more[++n] = team[i];
                }
            }
            return team_more;
        } else return null;
    }

    //  Searching team by name
    public static FootballTeam searchByName(FootballTeam team[], String search_name) {
        int search_id = -1; //  -1 - team with entered name doesn't exist
        for (int i = 0; i < team.length; i++) {
            if (search_name.equalsIgnoreCase(team[i].name)) {
                search_id = i;
            }
        }
        if (search_id != -1) {
            return team[search_id];
        } else return null;
    }

    public static void editTeams(String name, FootballTeam teams[], Scanner scan) {
        //editing team info
        int num = -1;
        for (int i = 0; i < teams.length; i++)
            if (name.equalsIgnoreCase(teams[i].name)) num = i;

        if (num != -1) {
            System.out.println("If you want to edit info: n - team name, c - team country, p - place in league, w - win number. For exit press 'q'");
            System.out.println("=> ");
            String in = scan.nextLine();
            switch (in) {
                case "n": {
                    System.out.println("Enter new name of team:");
                    String newvar = scan.nextLine();
                    teams[num].name = newvar;
                    break;
                }
                case "c": {
                    System.out.println("Enter new country of team:");
                    String newvar = scan.nextLine();
                    teams[num].country = newvar;
                    break;
                }
                case "p": {
                    System.out.println("Enter new league place:");
                    int newvar = scan.nextInt();
                    teams[num].league_place = newvar;
                    break;
                }
                case "w": {
                    System.out.println("Enter new number of wins:");
                    int newvar = scan.nextInt();
                    teams[num].win_num = newvar;
                    break;
                }
                case "q": {
                    break;
                }
                default: {
                    System.out.println("You entered wrong info. Try again:");
                    editTeams(name, teams, scan);

                }
            }
            System.out.println("Updated info about teams: " + "\n" + "" + teams[num].name + "\t" + teams[num].country + "\t" + teams[num].league_place + "\t" + teams[num].win_num);
        }
    }

    //  Main class with methods
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in, "cp1251");
        System.out.print("Enter the numbers of teams => ");
        int t_num = scanner.nextInt();  // number of teams
        FootballTeam team[] = setFootballTeamArray(t_num);  //  Entering info about teams

        System.out.println("\nTeam characteristics");
        showArray(team);  // output of the entered info

        // Wins more than average
        System.out.println("\nTeams with number of wins more than average:");
        FootballTeam[] team_more_avg = MoreThanAvg(team);
        showArray(team_more_avg);

        //  Arranging array by places
        sortLeaguePlace(team);
        System.out.println("\nArranged array of football teams by descending places in league:");
        showArray(team);

        //  Search by name
        System.out.println("\nSearching the team \nEnter the name of the team => ");
        scanner.nextLine();
        String search_name = scanner.nextLine();
        FootballTeam search_team = searchByName(team, search_name);
        if (search_team != null) {
            showTeam(search_team);
            editTeams(search_name, team, scanner);
        }
        else {
            System.out.println("Team with this name doesn't exist in the list");
        }
    }
}
