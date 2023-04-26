#include<iostream>
#include<string>
#include "Time.h"

using namespace std;
/** ________________________Constructor______________________ */
Time ::Time() // Default Constructor
{
    hour = 0;
    min = 0;
    sec = 0;
}
Time ::Time(int h, int m, int s)//Specific Constructor
{
    hour = h;
    min = m;
    sec = s;
}
/** _____________________Virtual Destructor_________________ */
Time :: ~Time()
{
    //none
}
/* _____________________Friend Function____________________ */
istream& getline(istream& is, Time& time, char delim)
{
    return is >> time;
}
/** _____________________Comparator__________________________ */
bool Time ::isEqualTo(Time time) const
{
    return this->hour == time.hour && this->min == time.min && this->sec == time.sec;
}
bool Time ::notEqualTo(Time &time) const
{
    return!(this->hour == time.hour && this->min == time.min && this->sec == time.sec);
}
/** _________________________Display_________________________ */
void Time ::displayTime() const
{
    cout<<"Time: "<<hour<<":"<<min<<":" << sec;
}
/** _________________________Setter__________________________ */
void Time ::setHour(int h){hour = h;}
void Time ::setMin(int m){min = m;}
void Time ::setSec(int s){sec = s;}
void Time ::setTime(int h, int m, int s)
{
    hour = h;
    min = m;
    sec = s;
}
/** _________________________Getter__________________________ */
int Time :: getHour() const{return hour;}
int Time :: getMin() const{return min;}
int Time :: getSec() const{return sec;}
int Time :: getTime() const
{
    getHour();
    getMin();
    getSec();
}
/** ___________Overloading Input Operator_____________________ */
istream & operator >>( istream & inputTime, Time & T )
{
    string sH,sM, sS;
    int h, m, s;

    getline(inputTime,sH,':');
    getline(inputTime,sM,':');
    getline(inputTime,sS,'\n');

    h=stoi(sH);
    m=stoi(sM);
    s= stoi(sS);

    T.setTime(h,m,s);
    return inputTime;
}
/** ___________Overloading Output Operator_____________________ */
ostream & operator <<( ostream & os, const Time & T )
{
    os<<"Time: "<<T.getHour()<<":"<<T.getMin()<<":" <<T.getSec();
    return os;
}
