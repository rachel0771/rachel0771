#include "AirQuality.h"

/** ________________________Constructor______________________ */
AirQuality::AirQuality()
{
    Time();
    Date();
    temp = 0.0;
    abs_humid = 0.0;
    rel_humid = 0.0;
}
AirQuality::AirQuality(int day, int mon, int year,
                       int hour, int min, int sec,
                       double t, double r, double a)
{
    date.setDate(day,mon,year);
    time.setTime(hour, min, sec);
    temp = t;
    rel_humid = r;
    abs_humid = a;
}

/** ________________________Display____________________________ */
void AirQuality::displayAirQuality() const
{
    cout << getDate() << getTime() << endl;
    cout <<"Temperature:"<< getTemp() << endl;
    cout << getAbsHumid() << " : "
         << getRelHumid() << endl;
}

/** ________________________Comparator__________________________ */
bool AirQuality:: isEqualTo(const AirQuality& otherAirQuality)
{
    return (date.isEqualTo(otherAirQuality.date)  &&
            time.isEqualTo(otherAirQuality.time) &&
            temp == otherAirQuality.temp &&
            abs_humid == otherAirQuality.abs_humid &&
            rel_humid == otherAirQuality.rel_humid);
}
bool AirQuality::notEqualTo(const AirQuality& otherAirQuality)
{
    return !(this->isEqualTo(otherAirQuality));
}

/** __________________________Setter___________________________ */
void AirQuality::setDate(int day, int mon, int year)
{
    date.setDate(day,mon,year);
}
void AirQuality::setTime(int hour, int min, int sec)
{
    time.setTime(hour,min,sec);
}
void AirQuality::setTemp(double t)
{
    temp = t;
}
void AirQuality::setRelHumid(double r)
{
    rel_humid = r;
}
void AirQuality::setAbsHumid(double a)
{
    abs_humid = a;
}
void AirQuality::setAirQuality(int day, int mon, int year,
                               int hour, int min, int sec,
                               double t, double r, double a)
{
    date.setDate(day,mon,year);
    time.setTime(hour, min, sec);
    temp = t;
    rel_humid = r;
    abs_humid = a;
}

/** ___________________________Getter________________________ */
Date AirQuality::getDate() const{return date;}
Time AirQuality::getTime()const{return time;}
double AirQuality::getTemp()const{return temp;}
double AirQuality::getRelHumid()const{return rel_humid;}
double AirQuality::getAbsHumid()const{return abs_humid;}