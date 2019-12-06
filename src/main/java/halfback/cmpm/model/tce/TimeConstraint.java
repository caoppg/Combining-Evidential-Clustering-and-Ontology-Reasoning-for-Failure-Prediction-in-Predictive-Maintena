package halfback.cmpm.model.tce;

import ca.pfv.spmf.algorithms.sequentialpatterns.clasp_AGP.dataStructures.Itemset;

/**
 * Implementation of a time constraint. <br>
 * A time constraint is a quadruplet made up of two event types and timestamps for each one.
 * 
 * @author <a href="mailto:carlos.miranda_lopez@insa-rouen.fr">Carlos Miranda</a>
 */
public class TimeConstraint {

    /**
     * Start and end timestamps for this time constraint.
     */
    private final long _startTime, _endTime;

    /**
     * Start end end event types (see {@link ca.pfv.spmf.algorithms.sequentialpatterns.clasp_AGP.dataStructures.Itemset})
     * for this time constraint.
     */
    private final NumberedItemset _startEv, _endEv;

    /**
     * Constructor.
     * 
     * @param startEv Start event type
     * @param startTime Start timestamp
     * @param endTime End timestamp
     * @param endEv End event type
     */
    public TimeConstraint(NumberedItemset startEv, long startTime, long endTime, NumberedItemset endEv) {
        _startEv = startEv;
        _endEv = endEv;
        _startTime = startTime;
        _endTime = endTime;
    }

    /**
     * Returns a string representation of this time constraint.
     * 
     * @return A string representation of this time constraint.
     */
    @Override
    public String toString() {
        return "\"" + _startEv.toString() + "\",\"" + _endEv.toString() + "\"," + _startTime + "," + _endTime;
    }

    /**
     * Returns whether this time constraint is equal to the other one based on event types comparisons.
     * 
     * @param o An object to check for equality.
     * 
     * @return Whether this time constraint is equal to the other one.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !getClass().equals(o.getClass())) return false;
        TimeConstraint tc = (TimeConstraint) o;
        return _startEv.equals(tc._startEv) && _endEv.equals(tc._endEv)
        		&& _startEv.getId() == tc._startEv.getId() && _endEv.getId() == tc._endEv.getId();
    }

    /**
     * Returns the hashcode of this time constraint.
     * 
     * @return The hashcode of this time constraint.
     */
    @Override
    public int hashCode() {
        return _startEv.hashCode();
    }

    /**
     * Returns the start event of this time constraint.
     * 
     * @return The start event of this time constraint.
     */
    public Itemset getStartEvent() {
        return _startEv;
    }

    /**
     * Returns the end event of this time constraint.
     * 
     * @return The end event of this time constraint.
     */
    public Itemset getEndEvent() {
        return _endEv;
    }

    /**
     * Returns a string representation of the interval.
     * 
     * @return A string representation of the interval.
     */
    public String intervalString() {
        return "[" + _startTime + ";" + _endTime + "]";
    }

    /**
     * Merges this time constraints to another one. The resulting time interval is given by the minimum of 
     * both minima and the maximum of both maxima.
     * 
     * @param tc The time constraint to be marged to.
     * 
     * @return A new instance of TimeConstraint with the resulting (widened) interval.
     */
    public TimeConstraint merge(TimeConstraint tc) {
        long startTime, endTime;
        if (_startTime < tc._startTime) {
            startTime = _startTime;
        } else {
            startTime = tc._startTime;
        }
        if (_endTime > tc._endTime) {
            endTime = _endTime;
        } else {
            endTime = tc._endTime;
        }
        return new TimeConstraint(_startEv, startTime, endTime, _endEv);
    }
}
