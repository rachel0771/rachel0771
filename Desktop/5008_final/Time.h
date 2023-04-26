#ifndef Time_h
#define Time_h

#include <iostream>
#include <string>

using namespace std;

class Time{
public:
    /* ________________________Constructor______________________ */
    Time();
    Time(int h, int m, int s);
    /* _____________________Virtual Destructor_________________ */
    virtual ~Time();
    /* _____________________Friend Function____________________ */
    friend istream& getline(istream& is, Time& time, char delim);
    /* _____________________Comparator__________________________ */
    bool isEqualTo(Time time) const;
    bool notEqualTo(Time &time)const;
    /* _________________________Display_________________________ */
    void displayTime() const;
    /* _________________________Setter__________________________ */
    void setHour(int h);
    void setMin(int m);
    void setSec(int s);
    void setTime(int h, int m, int s);
    /* _________________________Getter__________________________ */
    int getHour() const;
    int getMin() const;
    int getSec() const;
    int getTime() const;

private:
    //Time information
    int hour;
    int min;
    int sec;
};
/* ___________Overloading Output Operator_____________________ */
ostream & operator <<( ostream & os, const Time & T );
/* ____________Overloading Input Operator_____________________ */
istream & operator >>( istream & input, Time & T );

#endif