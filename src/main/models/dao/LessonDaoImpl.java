package main.models.dao;

import main.models.pojo.Lesson;
import main.utils.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class LessonDaoImpl implements LessonDao {
    private List<Lesson> constructFromResult(ResultSet result) {
        List<Lesson> lessons = new ArrayList<Lesson>(16);

        try {
            while (result.next()) {
                Lesson lesson = new Lesson(
                        result.getInt("id"),
                        new Date(result.getTimestamp("lesson_date").getTime()),
                        result.getInt("room"),
                        result.getString("description"),
                        result.getInt("study_group_id"));
                lessons.add(lesson);
            }
            result.close();
            result = null;
        } catch (SQLException e ) {
            e.printStackTrace();
        } finally {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return lessons;
    }

    public List<Lesson> getAll() {
        List<Lesson> lessons = null;
        Connection conn = null;
        Statement statement = null;
        try {
            conn = DatabaseManager.getConnectionFromPool();
            String query = "SELECT * FROM lesson;";
            statement = conn.createStatement();
            lessons = constructFromResult(statement.executeQuery(query));
            statement.close();
            statement = null;
            conn.close();
            conn = null;
        } catch (SQLException e ) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return lessons;
    }

    public List<Lesson> getById(int id) {
        List<Lesson> lessons = null;
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = DatabaseManager.getConnectionFromPool();
            String query = "SELECT * FROM lesson WHERE id=?;";
            statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            lessons = constructFromResult(statement.executeQuery(query));
            statement.close();
            statement = null;
            conn.close();
            conn = null;
        } catch (SQLException e ) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return lessons;
    }

    public List<Lesson> getByRoom(int room) {
        List<Lesson> lessons = null;
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = DatabaseManager.getConnectionFromPool();
            String query = "SELECT * FROM lesson WHERE room=?;";
            statement = conn.prepareStatement(query);
            statement.setInt(1, room);
            lessons = constructFromResult(statement.executeQuery(query));
            statement.close();
            statement = null;
            conn.close();
            conn = null;
        } catch (SQLException e ) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return lessons;
    }

    public boolean insertLesson(Lesson lesson) {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = DatabaseManager.getConnectionFromPool();
            String query = "INSERT INTO lesson (id, study_group_id, lesson_date, room," +
                    "description)" +
                    " VALUES (?, ?, ?, ?, ?);";
            statement = conn.prepareStatement(query);
            statement.setInt(1, lesson.getId());
            statement.setInt(2, lesson.getStudyGroup());
            statement.setTimestamp(3, new Timestamp(lesson.getLessonDate().getTime()));
            statement.setInt(4, lesson.getRoom());
            statement.setString(5, lesson.getDescription());
            statement.executeUpdate();
            statement.close();
            statement = null;
            conn.close();
            conn = null;
            return true;
        } catch (SQLException e ) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public boolean updateLesson(Lesson lesson) {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = DatabaseManager.getConnectionFromPool();
            String query = "UPDATE lesson SET study_group_id=?, lesson_date=?," +
                    " room=?, description=? WHERE id=?;";
            statement = conn.prepareStatement(query);
            statement.setInt(1, lesson.getStudyGroup());
            statement.setTimestamp(2, new Timestamp(lesson.getLessonDate().getTime()));
            statement.setInt(3, lesson.getRoom());
            statement.setString(4, lesson.getDescription());
            statement.setInt(5, lesson.getId());
            statement.executeUpdate();
            statement.close();
            statement = null;
            conn.close();
            conn = null;
            return true;
        } catch (SQLException e ) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public boolean deleteLesson(Lesson lesson) {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = DatabaseManager.getConnectionFromPool();
            String query = "DELETE FROM lesson WHERE id=?;";
            statement = conn.prepareStatement(query);
            statement.setInt(1, lesson.getId());
            statement.executeUpdate();
            statement.close();
            statement = null;
            conn.close();
            conn = null;
            return true;
        } catch (SQLException e ) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

}