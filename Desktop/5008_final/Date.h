#ifndef DATE_H
#define DATE_H

#include <iostream>
#include <string>

using namespace std;
class Date
{
public:
    /* ________________________Constructor______________________ */
    Date();
    Date(int dd, int mm, int yyyy);
    /* _____________________Friend Function____________________ */
    friend istream& getline(istream& is, Date& date, char delim);
    /* ________________________Comparator__________________________ */
    bool isEqualTo(const Date& otherDate);
    bool notEqualTo(const Date& otherDate);
    /* _________________________Display_________________________ */
    void displayDate() const;
    /* _________________________Setter__________________________ */
    void setDay(int dd);
    void setMonth(int mm);
    void setYear(int yyyy);
//    void setDate(int dd, int mm, int yyyy) const;
    void setDate(int dd, int mm, int yyyy);
    /* _________________________Getter__________________________ */
    int getDay() const;
    int getMonth() const;
    int getYear() const;
    int getDate() const;


private:
    //Date information
    int day;
    int month;
    int year;
};
/* ___________Overloading Output Operator_____________________ */
ostream& operator<<(ostream& os, const Date& D);

/* ___________Overloading intput Operator_____________________ */
istream& operator>>(istream& input, Date& D);

#endif // DATE_H
