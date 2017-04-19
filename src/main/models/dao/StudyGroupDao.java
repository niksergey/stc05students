package main.models.dao;

import main.models.pojo.StudyGroup;
import main.utils.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class StudyGroupDao implements StudyGroupDaoInterface {

    private List<StudyGroup> constructFromResult(ResultSet result) {
        List<StudyGroup> groups = new ArrayList<StudyGroup>(16);
        try {
            while (result.next()) {
                StudyGroup group = new StudyGroup(
                        result.getInt("id"),
                        result.getString("name"));
                groups.add(group);
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
        return groups;
    }

    public List<StudyGroup> getAllGroups() {
        List<StudyGroup> groups = null;
        Connection conn = null;
        Statement statement = null;
        try {
            conn = DatabaseManager.getConnectionFromPool();

            String query = "SELECT * FROM study_group;";
            statement = conn.createStatement();
            groups = constructFromResult(statement.executeQuery(query));
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
        return groups;
    }

    public List<StudyGroup> getById(int id) {
        List<StudyGroup> groups = null;
        PreparedStatement statement = null;
        Connection conn = null;
        try {
            conn = DatabaseManager.getConnectionFromPool();
            String query = "SELECT * FROM study_group WHERE id=?;";
            statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            groups = constructFromResult(statement.executeQuery(query));
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
        return groups;
    }

    public List<StudyGroup> getByName(String name) {
        List<StudyGroup> groups = null;
        PreparedStatement statement = null;
        Connection conn = null;
        try {
            conn = DatabaseManager.getConnectionFromPool();
            String query = "SELECT * FROM study_group WHERE name=?;";
            statement = conn.prepareStatement(query);
            statement.setString(1, name);
            groups = constructFromResult(statement.executeQuery(query));
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
        return groups;
    }

    public boolean insertStudyGroup(StudyGroup group) {
        PreparedStatement statement = null;
        Connection conn = null;
        try {
            conn = DatabaseManager.getConnectionFromPool();
            String query = "INSERT INTO study_group (id, name)" +
                    " VALUES (?, ?);";
            statement = conn.prepareStatement(query);
            statement.setInt(1, group.getId());
            statement.setString(2, group.getName());
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

    public boolean updateStudyGroup(StudyGroup group) {
        PreparedStatement statement = null;
        Connection conn = null;
        try {
            conn = DatabaseManager.getConnectionFromPool();
            String query = "UPDATE study_group SET name=? WHERE id=?;";
            statement = conn.prepareStatement(query);
            statement.setString(1, group.getName());
            statement.setInt(2, group.getId());
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

    public boolean deleteStudyGroup(StudyGroup group) {
        PreparedStatement statement = null;
        Connection conn = null;
        try {
            conn = DatabaseManager.getConnectionFromPool();
            String query = "DELETE FROM study_group WHERE id=?;";
            statement = conn.prepareStatement(query);
            statement.setInt(1, group.getId());
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
