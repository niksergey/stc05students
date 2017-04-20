package main.models.dao;

import main.models.pojo.Lesson;
import main.utils.DatabaseManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class LessonDaoImpl implements LessonDao {
    private final static Logger LOGGER = Logger.getLogger(LessonDaoImpl.class);

    private Lesson createEntity(ResultSet result) {
        Lesson lesson = null;
        try {
            lesson = new Lesson(
                    result.getInt("id"),
                    new Date(result.getTimestamp("lesson_date").getTime()),
                    result.getInt("room"),
                    result.getString("description"),
                    result.getInt("study_group_id"));
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return lesson;
    }

    public List<Lesson> getAll() {
        List<Lesson> lessons = new ArrayList<>(16);
        String query = "SELECT * FROM lesson;";

        try(Connection conn = DatabaseManager.getConnectionFromPool();
            Statement statement = conn.createStatement()) {
            try (ResultSet result = statement.executeQuery(query)) {
                while (result.next()) {
                    lessons.add(createEntity(result));
                }
            }
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return lessons;
    }

    public Lesson getById(int id) {
        String query = "SELECT * FROM lesson WHERE id=?;";
        Lesson lesson = null;

        try (Connection  conn = DatabaseManager.getConnectionFromPool();
             PreparedStatement statement = conn.prepareStatement(query)){
            statement.setInt(1, id);
            try(ResultSet result = statement.executeQuery()) {
                if (result.next())
                    lesson = createEntity(result);
            }
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return lesson;
    }

    public Lesson getByRoom(int room) {
        String query = "SELECT * FROM lesson WHERE room=?;";
        Lesson lesson = null;

        try (Connection conn = DatabaseManager.getConnectionFromPool();
             PreparedStatement statement = conn.prepareStatement(query)){
            statement.setInt(1, room);
            try(ResultSet result = statement.executeQuery()) {
                if (result.next())
                    lesson = createEntity(result);
            }
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return lesson;
    }

    public boolean insertLesson(Lesson lesson) {
        String query = "INSERT INTO lesson (id, study_group_id, lesson_date, room," +
                "description) VALUES (?, ?, ?, ?, ?);";
        try (Connection  conn = DatabaseManager.getConnectionFromPool();
             PreparedStatement statement = conn.prepareStatement(query)){
            statement.setInt(1, lesson.getId());
            statement.setInt(2, lesson.getStudyGroup());
            statement.setTimestamp(3, new Timestamp(lesson.getLessonDate().getTime()));
            statement.setInt(4, lesson.getRoom());
            statement.setString(5, lesson.getDescription());
            statement.executeUpdate();
            return true;
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateLesson(Lesson lesson) {
        String query = "UPDATE lesson SET study_group_id=?, lesson_date=?," +
                " room=?, description=? WHERE id=?;";
        try (Connection  conn = DatabaseManager.getConnectionFromPool();
             PreparedStatement statement = conn.prepareStatement(query)){
            statement.setInt(1, lesson.getStudyGroup());
            statement.setTimestamp(2, new Timestamp(lesson.getLessonDate().getTime()));
            statement.setInt(3, lesson.getRoom());
            statement.setString(4, lesson.getDescription());
            statement.setInt(5, lesson.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteLesson(Lesson lesson) {
        String query = "DELETE FROM lesson WHERE id=?;";
        try (Connection  conn = DatabaseManager.getConnectionFromPool();
             PreparedStatement statement = conn.prepareStatement(query)){
            statement.setInt(1, lesson.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return false;
    }
}