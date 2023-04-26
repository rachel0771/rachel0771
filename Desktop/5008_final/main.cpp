//CS5008  Ruimeng Zhang

#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
#include "Date.h"
#include "Time.h"
#include "AirQuality.h"
#include <vector>
#include <limits>

using namespace std;

ifstream input;

/** _________________Declaration of the methods _____________________ **/

/** Read the data*/
ostream& operator<<(ostream& output, const AirQuality& airQuality);
vector<AirQuality> readFile(string fileName);
void readMonthAndYear(int &month, int &year);
void readTime(int &hour, int &minute, int &second);

/** Process the data*/
double calculateAverage(const vector<AirQuality> &List,
                        int mm, int yyyy, int command);
AirQuality findAirQualityByDateTime(const vector<AirQuality>& AirQualityList,
                                    const Date& date, const Time& time);
double findHighest(const vector<AirQuality> &AirQualityList,
                   int month, int year, int command);
double findValueByType(const AirQuality& entry, int command);

/** Process the command*/
void processCommand(int command, const vector<AirQuality> &AirQualityList);

/** Display the data*/
void displayMenu();
void displayValuesAboveAverage(const vector<AirQuality> &AirQualityList,
                               int month, int year, int command, double average);

/** ______________________________Main Function__________________________*/
int main()
{
    /**Step 1: Display the data */
    string fileName = "/Users/meng/Desktop/5008_final/AirQualityUCI.csv";
    vector<AirQuality> AirQualityList = readFile(fileName);

    // Use a range-based for loop to display the air quality data
    for (const auto& airQuality : AirQualityList) {
        airQuality.displayAirQuality();
    }

    /**Step 2: Display the menu */
    // Use a do-while loop to display the menu and process user input
    int option;
    do {
        displayMenu();
        cin >> option;
        /** Step 3: Process the command */
        processCommand(option, AirQualityList);
    } while (option != 0);
}

/** _________________Overloading Output Operator_____________________ **/
ostream& operator<<(ostream& output, const AirQuality& airQuality)
{
    output << "Date: " << airQuality.getDate() << endl;
    output << "Time: " << airQuality.getTime() << endl;
    output << "Temperature: " << airQuality.getTemp() << endl;
    output << "Absolute Humidity: " << airQuality.getAbsHumid()<< endl;
    output << "Relative Humidity: " << airQuality.getRelHumid() << endl;
    return output;
}

/** _________________   Read the data      _____________________ **/
vector<AirQuality> readFile(string fileName)
{
    /*Step 1: open the file*/
    input.open(fileName);
    if (!input)
    {
        cout << "Error: No such file/" << fileName << endl;
        exit(EXIT_FAILURE); //Exit with failure if failed to open the file
    }


    /*Step 2: Process the data*/
    // Ignore the header line
    input.ignore(numeric_limits<streamsize>::max(), '\n');

    //set up the vector AirQualityList to store information
    vector<AirQuality> AirQualityList;
    string line;
    while (getline(input, line)) {
        stringstream ss(line);
        string sDay, sMonth, sYear, sTime, sTemp;
        double s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12, s13;

        // Extract date information: year, month and day
        getline(ss, sYear, '/');
        getline(ss, sMonth, '/');
        getline(ss, sDay, ',');

        // Extract time information: hour, minute and second
        getline(ss, sTime, ',');
        if (sTime.empty()) {
            continue;
        }

        int firstIdx = sTime.find(":");
        int hour = stoi(sTime.substr(0, firstIdx));
        int SecondIdx = sTime.find(':', firstIdx + 1);
        int minute = stoi(sTime.substr(firstIdx + 1, SecondIdx - firstIdx - 1));
        int second = stoi(sTime.substr(SecondIdx + 1));

        // Extract airQuality information: temperature and humidity
        getline(ss, sTemp, '\n');
        if (sTemp.empty()) {
            continue;
        }
        stringstream sTempStream(sTemp);
        vector<string> values;
        string value;
        while (getline(sTempStream, value, ','))
        {
            values.push_back(value);
        }
        s1 = stod(values[0]);
        s2 = stod(values[1]);
        s3 = stod(values[2]);
        s4 = stod(values[3]);
        s5 = stod(values[4]);
        s6 = stod(values[5]);
        s7 = stod(values[6]);
        s8 = stod(values[7]);
        s9 = stod(values[8]);
        s10 = stod(values[9]);
        s11 = stod(values[10]);
        s12 = stod(values[11]);
        s13 = stod(values[12]);

        if (s11 == -200 || s12 == -200 || s13 == -200) {
            continue; // skip this line if any variables is -200
        }

        // Create a new AirQuality object and push it to the vector
        AirQuality airQuality;
        airQuality.setDate(stoi(sDay), stoi(sMonth), stoi(sYear));
        airQuality.setTime(hour, minute, second);
        airQuality.setTemp(s11);
        airQuality.setAbsHumid(s12);
        airQuality.setRelHumid(s13);

        AirQualityList.push_back(airQuality);
    }
    return AirQualityList;
}


/**_______________________Display the menu_____________________________**/
void displayMenu()
{
    cout << endl;
    cout << "==================================Menu==========================" << endl;
    cout <<"1. Display the average temperature for the month" << endl;
    cout <<"2. Display the average relative humidity for the month"<< endl;
    cout <<"3. Display the average absolute humidity temperature for the month"<< endl;
    cout << "4. Display temperature and relative humidity at a specific date and time" << endl;
    cout << "5. Display the highest temperature for the month" << endl;
    cout << "6. Display the highest relative humidity for the month" << endl;
    cout << "7. Display the highest absolute humidity for the month" << endl;
    cout << "8. Display dates and times when temperature is higher than average" << endl;
    cout << "9. Display dates and times when relative humidity is higher than average" << endl;
    cout << "10. Display dates and times when absolute humidity is higher than average" << endl;
    cout << "Enter your command (0 to quit): ";
}
/**_______________________Process the command_____________________________**/
void processCommand(int command, const vector<AirQuality> &AirQualityList) {
    int month, year, day, hour, minute, second;
    Date date;
    Time time;
    AirQuality airInfo;

    switch (command) {
        case 0:
            cout << "End" << endl;
            break;
        case 1:
        {
            readMonthAndYear(month,year);
            double avgTemp = calculateAverage(AirQualityList, month, year, 1);
            cout<<"The average temperature is: " << avgTemp << endl;
            break;
        }
        case 2:
        {
            readMonthAndYear(month,year);
            double avgRel = calculateAverage(AirQualityList, month,year,2);
            cout << "The average relative humidity is: " << avgRel << endl;
            break;
        }
        case 3:
        {
            readMonthAndYear(month, year);
            double avgAbs = calculateAverage(AirQualityList, month, year, 3);
            if (avgAbs == numeric_limits<double>::lowest()) {
                cout << "Error: no such entry." << endl;
            } else {
                cout << "The average absolute humidity is: " << avgAbs << endl;
            }
            break;
        }
        case 4:
        {
            cout << "Enter day: ";
            cin >> day;
            readMonthAndYear(month, year);
            date.setDate(day,month,year);
            date.getDate();
            readTime(hour,minute, second);
            time.setTime(hour, minute, second);
            airInfo = findAirQualityByDateTime(AirQualityList, date,time);
            cout << " The temperature for this specific time slot is: " << airInfo.getTemp() << endl;
            cout << " The relative Humidity for this time slot is: " << airInfo.getRelHumid() << endl;

            break;
        }

        case 5:
        {
            readMonthAndYear(month,year);
            double highest = findHighest(AirQualityList, month, year, 5);
            if (highest == numeric_limits<double>::lowest()) {
                cout << "Error: No such entry." << endl;
            } else {
                cout << "The highest temperature is: " << highest << endl;
            }
            break;
            break;
        }
        case 6:
        {
            readMonthAndYear(month,year);
            double highest = findHighest(AirQualityList, month, year, 6);
            if (highest == numeric_limits<double>::lowest()) {
                cout << "Error: No such entry." << endl;
            } else {
                cout << "The highest relative humidity is: " << highest << endl;
            }
            break;
        }
        case 7:
        {
            readMonthAndYear(month, year);
            double highest = findHighest(AirQualityList, month, year, 7);
            if (highest == numeric_limits<double>::lowest()) {
                cout << "Error: No such entry." << endl;
            } else {
                cout << "The highest absolute humidity is: " << highest << endl;
            }
            break;
        }
        case 8:
        {
            readMonthAndYear(month, year);
            double average = calculateAverage(AirQualityList, month, year, 1);
            cout <<"The average temperature in " << month << "/" << year<<" is " << average << endl;
            cout << ". The dates and times that temperature is higher than average is: " <<endl;
            displayValuesAboveAverage(AirQualityList, month, year, 8, average);
            break;
        }
        case 9:
        {
            readMonthAndYear(month, year);
            double average = calculateAverage(AirQualityList, month, year, 2);
            cout <<"The average relative humidity in " << month << "/" << year<<" is " << average << endl;
            cout << ". The dates and times that relative humidity is higher than average is: " <<endl;
            displayValuesAboveAverage(AirQualityList, month, year, 9,average);
            break;
        }
        case 10:
        {
            readMonthAndYear(month, year);
            double average = calculateAverage(AirQualityList, month, year, 3);
            cout <<"The average absolute humidity in " << month << "/" << year<<" is " << average << endl;
            cout << ". The dates and times that absolute humidity is higher than average is: " <<endl;
            displayValuesAboveAverage(AirQualityList, month, year, 10,average);
            break;
        }

        default:
            cout << "Error: Invalid input." << endl;
    }
}
/* Helper Function: Read the month and year*/
void readMonthAndYear(int &month, int &year)
{
    cout << "Enter month: ";
    cin >> month;
    cout << "Enter year: ";
    cin >> year;
}
/* Helper Function: Read the time*/
void readTime(int &hour, int &minute, int &second)
{
    cout << "Enter hour: ";
    cin >> hour;
    cout << "Enter minute: ";
    cin >> minute;
    cout<< "Enter seconds: ";
    cin >> second;
}
/**__________________Calculate average air quality value__________________**/
double calculateAverage(const vector<AirQuality> &List,
                        int mm, int yyyy, int command)
{
    double sum = 0;
    int count = 0;
    bool found = false;
    double lowest = numeric_limits<double>::lowest();

    for (const AirQuality &entry : List) {
        if (entry.getDate().getMonth() == mm && entry.getDate().getYear() == yyyy) {
            found = true;
            switch (command) {
                case 1:
                    sum += entry.getTemp();
                    break;
                case 2:
                    sum += entry.getRelHumid();
                    break;
                case 3:
                    sum += entry.getAbsHumid();
                    break;
                default:
                    cout << "Invalid type argument" << endl;
                    return lowest;
            }
            count++;
        }
    }
    if (found) {
        double average = count > 0 ? sum / count : 0;
        return average;
    } else {
        return lowest;
    }
}
/** _________________________Find AirQuality by date and time __________________*/
AirQuality findAirQualityByDateTime(const vector<AirQuality>& AirQualityList,
                                    const Date& date, const Time& time)
{
    for (const AirQuality& dataType : AirQualityList)
    {
        if (dataType.getDate().isEqualTo(date) && dataType.getTime().isEqualTo(time))
        {
            return dataType;
        }
    }
    // Return default constructor if no matched record
    return AirQuality();
}
/** __________________________Find highest value in month______________________*/
double findHighest(const vector<AirQuality> &AirQualityList,
                   int month, int year, int command)
{
    double highest = std::numeric_limits<double>::lowest();
    bool found = false;
    for (const AirQuality &entry : AirQualityList) {
        if (entry.getDate().getMonth() == month && entry.getDate().getYear() == year) {
            double value = findValueByType(entry, command);
            if (value > highest) {
                highest = value;
                found = true;
            }
        }
    }
    if(!found){
        // If no matched record
        throw runtime_error("Error: No matched data");
    }
    return highest;
}

/** ______________________________Find value by type__________________________*/
double findValueByType(const AirQuality& object, int command)
{
    double value = 0;
    switch(command) {
        case 5:
            value = object.getTemp();
            break;
        case 6:
            value = object.getRelHumid();
            break;
        case 7:
            value = object.getAbsHumid();
            break;
        default:
            // If the type is invalid
            throw invalid_argument("Invalid command.");
    }
    return value;
}

/** ______________________________Display values that higher than average___________________*/
void displayValuesAboveAverage(const vector<AirQuality> &AirQualityList,
                               int month, int year, int command, double average)
{
    bool found = false;
    double value;
    for (const AirQuality &object : AirQualityList) {
        if (object.getDate().getMonth() == month && object.getDate().getYear() == year) {
            found = true;
            switch(command)
            {
                case 8:
                    value = object.getTemp();
                    break;
                case 9:
                    value = object.getRelHumid();
                    break;
                case 10:
                    value = object.getAbsHumid();
                    break;
            }
            if (value > average)
            {
                object.getDate().displayDate();
                cout << "; ";
                object.getTime().displayTime();
                cout << "; ";
                cout << value << endl;
            }
        }
    }
    if (!found){
        cout<<"Error: No such entry"<<endl;
    }
}
