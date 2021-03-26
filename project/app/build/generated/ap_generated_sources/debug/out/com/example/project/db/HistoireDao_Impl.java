package com.example.project.db;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class HistoireDao_Impl implements HistoireDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Histoire> __insertionAdapterOfHistoire;

  private final EntityDeletionOrUpdateAdapter<Histoire> __deletionAdapterOfHistoire;

  private final EntityDeletionOrUpdateAdapter<Histoire> __updateAdapterOfHistoire;

  public HistoireDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfHistoire = new EntityInsertionAdapter<Histoire>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Histoire` (`id`,`intitulee`,`question`,`reponse`,`aide`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Histoire value) {
        stmt.bindLong(1, value.getId());
        if (value.getIntitulee() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getIntitulee());
        }
        if (value.getQuestion() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getQuestion());
        }
        stmt.bindLong(4, value.getReponse());
        if (value.getAide() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getAide());
        }
      }
    };
    this.__deletionAdapterOfHistoire = new EntityDeletionOrUpdateAdapter<Histoire>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Histoire` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Histoire value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfHistoire = new EntityDeletionOrUpdateAdapter<Histoire>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Histoire` SET `id` = ?,`intitulee` = ?,`question` = ?,`reponse` = ?,`aide` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Histoire value) {
        stmt.bindLong(1, value.getId());
        if (value.getIntitulee() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getIntitulee());
        }
        if (value.getQuestion() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getQuestion());
        }
        stmt.bindLong(4, value.getReponse());
        if (value.getAide() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getAide());
        }
        stmt.bindLong(6, value.getId());
      }
    };
  }

  @Override
  public void insert(final Histoire question) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfHistoire.insert(question);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public long[] insertAll(final Histoire... question) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long[] _result = __insertionAdapterOfHistoire.insertAndReturnIdsArray(question);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Histoire question) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfHistoire.handle(question);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Histoire question) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfHistoire.handle(question);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Histoire> getAll() {
    final String _sql = "SELECT * FROM Histoire";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfIntitulee = CursorUtil.getColumnIndexOrThrow(_cursor, "intitulee");
      final int _cursorIndexOfQuestion = CursorUtil.getColumnIndexOrThrow(_cursor, "question");
      final int _cursorIndexOfReponse = CursorUtil.getColumnIndexOrThrow(_cursor, "reponse");
      final int _cursorIndexOfAide = CursorUtil.getColumnIndexOrThrow(_cursor, "aide");
      final List<Histoire> _result = new ArrayList<Histoire>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Histoire _item;
        _item = new Histoire();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpIntitulee;
        _tmpIntitulee = _cursor.getString(_cursorIndexOfIntitulee);
        _item.setIntitulee(_tmpIntitulee);
        final String _tmpQuestion;
        _tmpQuestion = _cursor.getString(_cursorIndexOfQuestion);
        _item.setQuestion(_tmpQuestion);
        final int _tmpReponse;
        _tmpReponse = _cursor.getInt(_cursorIndexOfReponse);
        _item.setReponse(_tmpReponse);
        final String _tmpAide;
        _tmpAide = _cursor.getString(_cursorIndexOfAide);
        _item.setAide(_tmpAide);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Histoire findByName(final String id) {
    final String _sql = "SELECT * FROM Histoire WHERE id LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, id);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfIntitulee = CursorUtil.getColumnIndexOrThrow(_cursor, "intitulee");
      final int _cursorIndexOfQuestion = CursorUtil.getColumnIndexOrThrow(_cursor, "question");
      final int _cursorIndexOfReponse = CursorUtil.getColumnIndexOrThrow(_cursor, "reponse");
      final int _cursorIndexOfAide = CursorUtil.getColumnIndexOrThrow(_cursor, "aide");
      final Histoire _result;
      if(_cursor.moveToFirst()) {
        _result = new Histoire();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _result.setId(_tmpId);
        final String _tmpIntitulee;
        _tmpIntitulee = _cursor.getString(_cursorIndexOfIntitulee);
        _result.setIntitulee(_tmpIntitulee);
        final String _tmpQuestion;
        _tmpQuestion = _cursor.getString(_cursorIndexOfQuestion);
        _result.setQuestion(_tmpQuestion);
        final int _tmpReponse;
        _tmpReponse = _cursor.getInt(_cursorIndexOfReponse);
        _result.setReponse(_tmpReponse);
        final String _tmpAide;
        _tmpAide = _cursor.getString(_cursorIndexOfAide);
        _result.setAide(_tmpAide);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
