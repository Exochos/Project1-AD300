package com.jeremyward;
/**
 * @param  riverI a reference to the river index.
 * @param  month a reference to the month index.
 * @param day a reference to the day index
 * @param arsenic a reference to the amount of arsenic at any point.
 * @param lead a reference to the amount of lead at any point.
 * @param fertilizer a reference to the amount of fertilizer at any point.
 * @param  pesticide  a reference to the amount of pesticide at any point.
 */
public record PollutionData(int riverI, int month, int day, int arsenic, int lead, double fertilizer, double pesticide) {
}
