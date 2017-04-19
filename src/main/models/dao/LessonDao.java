package main.models.dao;

import main.models.pojo.Lesson;
import main.utils.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class LessonDao implements LessonDaoInterface {
    public List<Lesson> getAll() {
        List<Lesson> lessons = new ArrayList<Lesson>(30);
        ResultSet result;
        try {
            Connection conn = DatabaseManager.getConnectionFromPool();
            String query = "SELECT * FROM lesson;";
            Statement statement = conn.createStatement();
            result = statement.executeQuery(query);
//            int id, Date lessonDate, int room, String description, int studyGroup
            while (result.next()) {
                Lesson lesson = new Lesson(
                        result.getInt("id"),
                        new Date(result.getTimestamp("lesson_date").getTime()),
                        result.getInt("room"),
                        result.getString("description"),
                        result.getInt("study_group_id"));
                lessons.add(lesson);
            }
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return lessons;
    }

    public List<Lesson> getById(int id) {
        List<Lesson> lessons = new ArrayList<Lesson>(30);
        ResultSet result;
        try {
            Connection conn = DatabaseManager.getConnectionFromPool();
            String query = "SELECT * FROM lesson WHERE id=?;";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            result = statement.executeQuery();
//            int id, Date lessonDate, int room, String description, int studyGroup
            while (result.next()) {
                Lesson lesson = new Lesson(
                        result.getInt("id"),
                        new Date(result.getTimestamp("lesson_date").getTime()),
                        result.getInt("room"),
                        result.getString("description"),
                        result.getInt("study_group_id"));
                lessons.add(lesson);
            }
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return lessons;
    }

    public List<Lesson> getByRoom(int room) {
        List<Lesson> lessons = new ArrayList<Lesson>(30);
        ResultSet result;
        try {
            Connection conn = DatabaseManager.getConnectionFromPool();
            String query = "SELECT * FROM lesson WHERE room=?;";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, room);
            result = statement.executeQuery();
//            int id, Date lessonDate, int room, String description, int studyGroup
            while (result.next()) {
                Lesson lesson = new Lesson(
                        result.getInt("id"),
                        new Date(result.getTimestamp("lesson_date").getTime()),
                        result.getInt("room"),
                        result.getString("description"),
                        result.getInt("study_group_id"));
                lessons.add(lesson);
            }
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return lessons;
    }

    public boolean insertLesson(Lesson lesson) {
        try {
            Connection conn = DatabaseManager.getConnectionFromPool();
            String query = "INSERT INTO lesson (id, study_group_id, lesson_date, room," +
                    "description)" +
                    " VALUES (?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, lesson.getId());
            preparedStatement.setInt(2, lesson.getStudyGroup());
            preparedStatement.setTimestamp(3, new Timestamp(lesson.getLessonDate().getTime()));
            preparedStatement.setInt(4, lesson.getRoom());
            preparedStatement.setString(5, lesson.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean updateLesson(Lesson lesson) {
        try {
            Connection conn = DatabaseManager.getConnectionFromPool();
            String query = "UPDATE lesson SET study_group_id=?, lesson_date=?," +
                    " room=?, description WHERE id=?;";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, lesson.getStudyGroup());
            preparedStatement.setTimestamp(2, new Timestamp(lesson.getLessonDate().getTime()));
            preparedStatement.setInt(3, lesson.getRoom());
            preparedStatement.setString(4, lesson.getDescription());
            preparedStatement.setInt(5, lesson.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean deleteLesson(Lesson lesson) {
        try {
            Connection conn = DatabaseManager.getConnectionFromPool();
            String query = "DELETE FROM lesson WHERE id=?;";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, lesson.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return true;
    }

}