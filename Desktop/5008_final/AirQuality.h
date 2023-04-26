#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
#include "Date.h"
#include "Time.h"

using namespace std;

class AirQuality {
public:
    /* ________________________Constructor________________________ */
    AirQuality();
    AirQuality(int day, int mon, int year,
               int hour, int min, int sec,
               double t, double r, double a);

    /* ________________________Display____________________________ */
    void displayAirQuality() const;

    /* ________________________Comparator__________________________ */
    bool isEqualTo(const AirQuality& otherAirQuality);
    bool notEqualTo(const AirQuality& otherAirQuality);

    /* __________________________Setter___________________________ */
    void setDate(int day, int mon, int year);
    void setTime(int hour, int min, int sec);
    void setTemp(double temp);
    void setRelHumid(double r);
    void setAbsHumid(double a);
    void setAirQuality(int dd, int mm, int yyyy,
                       int hour, int min, int sec,
                       double t, double r, double a);

    /* ___________________________Getter________________________ */
    Date getDate() const;
    Time getTime()const;
    double getTemp()const;
    double getRelHumid()const;
    double getAbsHumid()const;

private:
    Date date;
    Time time;
    double temp;
    double rel_humid;
    double abs_humid;
};

