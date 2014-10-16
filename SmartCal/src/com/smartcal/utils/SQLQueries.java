package com.smartcal.utils;

/**
 * Constants class created to hold and centralize all sql queries
 * @author Gorjan
 *
 */
public class SQLQueries {

	// users repository queries
	public static final String SQL_GET_ALL_USERS = "select distinct u.* from smartcaldb.users as u;";
	public static final String SQL_GET_USER_BY_ID = "select * from smartcaldb.users where users.userid = ?;";
	public static final String SQL_ADD_USER = "insert into users (name,email,signupdate,username,password)"
			+ " values (\"?\",\"?\",\"?\",\"?\",\"?\");";
	public static final String SQL_DELETE_USER = "delete from smartcaldb.users where smartcaldb.users.userid = ?;";
	public static final String SQL_GET_ATTENDIES = "select users.* "
			+ "from smartcaldb.users, smartcaldb.attending "
			+ "where users.userid = attending.uid and attending.eid = ?;";


	// events repository queries
	public static final String SQL_GET_ALL_EVENTS = "select distinct e.* from smartcaldb.events as e;";
	public static final String SQL_ADD_EVENT = "insert into smartcaldb.`events` (title, description, url, starttime, endtime) values (?,?,?,?,?);";
	public static final String SQL_UPDATE_EVENT = "update smartcaldb.events set title = ?, description = ?, "
			+ " url = ?, starttime = ?, endtime = ? where `events`.eventid = ?;";
	public static final String SQL_REMOVE_EVENT = "delete from smartcaldb.events where smartcaldb.events.eventid = ?;";
	public static final String SQL_GET_EVENT_BY_ID = "select * from smartcaldb.events where events.eventid = ?;";
	public static final String SQL_USER_EVENT_ATTEND = "insert into smartcaldb.attending (uid,eid) values (?,?);";
	public static final String SQL_USER_EVENT_CANCEL = "delete from smartcaldb.attending where smartcaldb.attending.uid = ? and "
			+ "smartcaldb.attending.eid = ?";
	public static final String SQL_GET_PAST_EVENTS_FOR_USER = "select e.* "
			+ "from smartcaldb.events as e, smartcaldb.attending as a "
			+ "where a.uid = ? and a.eid = e.eventid and e.starttime <= ?";
	public static final String SQL_GET_UPCOMING_EVENTS_FOR_USER = "select e.* "
			+ "from smartcaldb.events as e, smartcaldb.attending as a "
			+ "where a.uid = ? and a.eid = e.eventid and e.starttime >= ?";
	public static final String SQL_GET_EVENTS_BY_DATE = "select e.* "
			+ "from smartcaldb.events as e" + "where e.starttime = ?";


	//friends repository queries
	public static final String SQL_GET_FRIENDS_FOR_USER = "select u.* "
			+ "from smartcaldb.friend as f, smartcaldb.users as u "
			+ "where f.user1 = ? and f.user2 = u.userid";
	public static final String SQL_GET_FRIENDS_WITH_SIMILARITY = "select u.* "
			+ "from smartcaldb.friend as f, smartcaldb.users as u "
			+ "where f.user1 = ? and f.similarity > ? and f.user2 = u.userid";
	public static final String SQL_GET_FRIENDS_ATTENDING = "select u.* "
			+ "from smartcaldb.events as e, smartcaldb.attending as a,"
			+ " smartcaldb.friend as f, smartcaldb.users as u "
			+ "where e.eventid = ? and f.user1 = ? and a.uid = f.user2 and a.eid = ? and a.uid = u.userid";
	public static final String SQL_CREATE_FRIENDSHIP = "insert into smartcaldb.friend (user1, user2, similarity) values (?,?,?);";

}
