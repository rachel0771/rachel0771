#include "Date.h"
#include <string>
#include <iostream>

using namespace std;
/* ________________________Constructor______________________ */
Date::Date() // Default Constructor
{
    day=1;
    month=1;
    year=1900;
}
Date::Date(int dd, int mm, int yyyy) // Specific Constructor
{
    day=dd;
    month=mm;
    year=yyyy;
}
/* _____________________Friend Function____________________ */
istream& getline(istream& is, Date& date, char delim)
{
    return is >> date;
}
/* ________________________Comparator__________________________ */
bool Date::isEqualTo(const Date& otherDate) //is equal to
{
    return this->day == otherDate.day && this->month == otherDate.month && this->year == otherDate.year;
}
bool Date::notEqualTo(const Date& otherDate)// not equal to
{
    return !(this->day == otherDate.day && this->month == otherDate.month && this->year == otherDate.year);
}

/* _________________________Display_________________________ */
void Date::displayDate() const
{
    cout<<"  Date: " << day<<"/"<< month<<"/"<<year;
}
/* _________________________Setter__________________________ */
void Date::setDay(int dd){day = dd;}
void Date::setMonth(int mm){month = mm;}
void Date::setYear(int yyyy){year = yyyy;}
void Date::setDate(int dd, int mm, int yyyy)
{
    day = dd;
    month = mm;
    year = yyyy;
}
/* _________________________Getter__________________________ */
int Date::getDay() const{return day;}
int Date::getMonth() const{return month;}
int Date::getYear() const{return year;}
int Date::getDate() const
{
    getDay();
    getMonth();
    getYear();
}


/* ___________Overloading intput Operator_____________________ */
istream & operator >>( istream & input, Date & D )
{
    string stringdd, stringmm, stringyy;
    int dd,mm, yyyy;

    getline(input, stringdd, '/');
    getline(input, stringmm,'/');
    getline(input, stringyy,' ');

    dd=stoi(stringdd);
    mm=stoi(stringmm);
    yyyy=stoi(stringyy);

    D.setDate(dd, mm, yyyy);

    return input;
}
/* ___________Overloading Output Operator_____________________ */
ostream & operator <<( ostream & os, const Date & D)
{
    os << "Date:  " << D.getDay() << D.getMonth() <<D.getYear()<<'\n';
    return os;
}
