package com.example.flashcardapp.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.flashcardapp.data.local.entity.Word;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@SuppressWarnings({"unchecked", "deprecation"})
public final class WordDao_Impl implements WordDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Word> __insertionAdapterOfWord;

  private final EntityDeletionOrUpdateAdapter<Word> __deletionAdapterOfWord;

  private final EntityDeletionOrUpdateAdapter<Word> __updateAdapterOfWord;

  public WordDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfWord = new EntityInsertionAdapter<Word>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `words` (`id`,`originalWord`,`meaning`,`exampleSentence`,`nextReviewDate`,`reviewInterval`,`easinessFactor`,`category`,`imageUrl`,`languageCode`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Word entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getOriginalWord() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getOriginalWord());
        }
        if (entity.getMeaning() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getMeaning());
        }
        if (entity.getExampleSentence() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getExampleSentence());
        }
        statement.bindLong(5, entity.getNextReviewDate());
        statement.bindLong(6, entity.getReviewInterval());
        statement.bindDouble(7, entity.getEasinessFactor());
        if (entity.getCategory() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getCategory());
        }
        if (entity.getImageUrl() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getImageUrl());
        }
        if (entity.getLanguageCode() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getLanguageCode());
        }
      }
    };
    this.__deletionAdapterOfWord = new EntityDeletionOrUpdateAdapter<Word>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `words` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Word entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfWord = new EntityDeletionOrUpdateAdapter<Word>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `words` SET `id` = ?,`originalWord` = ?,`meaning` = ?,`exampleSentence` = ?,`nextReviewDate` = ?,`reviewInterval` = ?,`easinessFactor` = ?,`category` = ?,`imageUrl` = ?,`languageCode` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Word entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getOriginalWord() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getOriginalWord());
        }
        if (entity.getMeaning() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getMeaning());
        }
        if (entity.getExampleSentence() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getExampleSentence());
        }
        statement.bindLong(5, entity.getNextReviewDate());
        statement.bindLong(6, entity.getReviewInterval());
        statement.bindDouble(7, entity.getEasinessFactor());
        if (entity.getCategory() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getCategory());
        }
        if (entity.getImageUrl() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getImageUrl());
        }
        if (entity.getLanguageCode() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getLanguageCode());
        }
        statement.bindLong(11, entity.getId());
      }
    };
  }

  @Override
  public Object insertWord(final Word word, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfWord.insert(word);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteWord(final Word word, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfWord.handle(word);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateWord(final Word word, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfWord.handle(word);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Word>> getAllWords() {
    final String _sql = "SELECT * FROM words";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"words"}, new Callable<List<Word>>() {
      @Override
      @NonNull
      public List<Word> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfOriginalWord = CursorUtil.getColumnIndexOrThrow(_cursor, "originalWord");
          final int _cursorIndexOfMeaning = CursorUtil.getColumnIndexOrThrow(_cursor, "meaning");
          final int _cursorIndexOfExampleSentence = CursorUtil.getColumnIndexOrThrow(_cursor, "exampleSentence");
          final int _cursorIndexOfNextReviewDate = CursorUtil.getColumnIndexOrThrow(_cursor, "nextReviewDate");
          final int _cursorIndexOfReviewInterval = CursorUtil.getColumnIndexOrThrow(_cursor, "reviewInterval");
          final int _cursorIndexOfEasinessFactor = CursorUtil.getColumnIndexOrThrow(_cursor, "easinessFactor");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfLanguageCode = CursorUtil.getColumnIndexOrThrow(_cursor, "languageCode");
          final List<Word> _result = new ArrayList<Word>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Word _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpOriginalWord;
            if (_cursor.isNull(_cursorIndexOfOriginalWord)) {
              _tmpOriginalWord = null;
            } else {
              _tmpOriginalWord = _cursor.getString(_cursorIndexOfOriginalWord);
            }
            final String _tmpMeaning;
            if (_cursor.isNull(_cursorIndexOfMeaning)) {
              _tmpMeaning = null;
            } else {
              _tmpMeaning = _cursor.getString(_cursorIndexOfMeaning);
            }
            final String _tmpExampleSentence;
            if (_cursor.isNull(_cursorIndexOfExampleSentence)) {
              _tmpExampleSentence = null;
            } else {
              _tmpExampleSentence = _cursor.getString(_cursorIndexOfExampleSentence);
            }
            final long _tmpNextReviewDate;
            _tmpNextReviewDate = _cursor.getLong(_cursorIndexOfNextReviewDate);
            final int _tmpReviewInterval;
            _tmpReviewInterval = _cursor.getInt(_cursorIndexOfReviewInterval);
            final float _tmpEasinessFactor;
            _tmpEasinessFactor = _cursor.getFloat(_cursorIndexOfEasinessFactor);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final String _tmpLanguageCode;
            if (_cursor.isNull(_cursorIndexOfLanguageCode)) {
              _tmpLanguageCode = null;
            } else {
              _tmpLanguageCode = _cursor.getString(_cursorIndexOfLanguageCode);
            }
            _item = new Word(_tmpId,_tmpOriginalWord,_tmpMeaning,_tmpExampleSentence,_tmpNextReviewDate,_tmpReviewInterval,_tmpEasinessFactor,_tmpCategory,_tmpImageUrl,_tmpLanguageCode);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getWordsToReview(final long currentDate,
      final Continuation<? super List<Word>> $completion) {
    final String _sql = "SELECT * FROM words WHERE nextReviewDate <= ? ORDER BY nextReviewDate ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, currentDate);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Word>>() {
      @Override
      @NonNull
      public List<Word> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfOriginalWord = CursorUtil.getColumnIndexOrThrow(_cursor, "originalWord");
          final int _cursorIndexOfMeaning = CursorUtil.getColumnIndexOrThrow(_cursor, "meaning");
          final int _cursorIndexOfExampleSentence = CursorUtil.getColumnIndexOrThrow(_cursor, "exampleSentence");
          final int _cursorIndexOfNextReviewDate = CursorUtil.getColumnIndexOrThrow(_cursor, "nextReviewDate");
          final int _cursorIndexOfReviewInterval = CursorUtil.getColumnIndexOrThrow(_cursor, "reviewInterval");
          final int _cursorIndexOfEasinessFactor = CursorUtil.getColumnIndexOrThrow(_cursor, "easinessFactor");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfLanguageCode = CursorUtil.getColumnIndexOrThrow(_cursor, "languageCode");
          final List<Word> _result = new ArrayList<Word>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Word _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpOriginalWord;
            if (_cursor.isNull(_cursorIndexOfOriginalWord)) {
              _tmpOriginalWord = null;
            } else {
              _tmpOriginalWord = _cursor.getString(_cursorIndexOfOriginalWord);
            }
            final String _tmpMeaning;
            if (_cursor.isNull(_cursorIndexOfMeaning)) {
              _tmpMeaning = null;
            } else {
              _tmpMeaning = _cursor.getString(_cursorIndexOfMeaning);
            }
            final String _tmpExampleSentence;
            if (_cursor.isNull(_cursorIndexOfExampleSentence)) {
              _tmpExampleSentence = null;
            } else {
              _tmpExampleSentence = _cursor.getString(_cursorIndexOfExampleSentence);
            }
            final long _tmpNextReviewDate;
            _tmpNextReviewDate = _cursor.getLong(_cursorIndexOfNextReviewDate);
            final int _tmpReviewInterval;
            _tmpReviewInterval = _cursor.getInt(_cursorIndexOfReviewInterval);
            final float _tmpEasinessFactor;
            _tmpEasinessFactor = _cursor.getFloat(_cursorIndexOfEasinessFactor);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final String _tmpLanguageCode;
            if (_cursor.isNull(_cursorIndexOfLanguageCode)) {
              _tmpLanguageCode = null;
            } else {
              _tmpLanguageCode = _cursor.getString(_cursorIndexOfLanguageCode);
            }
            _item = new Word(_tmpId,_tmpOriginalWord,_tmpMeaning,_tmpExampleSentence,_tmpNextReviewDate,_tmpReviewInterval,_tmpEasinessFactor,_tmpCategory,_tmpImageUrl,_tmpLanguageCode);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<Integer> getTotalWordsCount() {
    final String _sql = "SELECT COUNT(*) FROM words";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"words"}, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getAllWordsList(final Continuation<? super List<Word>> $completion) {
    final String _sql = "SELECT * FROM words";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Word>>() {
      @Override
      @NonNull
      public List<Word> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfOriginalWord = CursorUtil.getColumnIndexOrThrow(_cursor, "originalWord");
          final int _cursorIndexOfMeaning = CursorUtil.getColumnIndexOrThrow(_cursor, "meaning");
          final int _cursorIndexOfExampleSentence = CursorUtil.getColumnIndexOrThrow(_cursor, "exampleSentence");
          final int _cursorIndexOfNextReviewDate = CursorUtil.getColumnIndexOrThrow(_cursor, "nextReviewDate");
          final int _cursorIndexOfReviewInterval = CursorUtil.getColumnIndexOrThrow(_cursor, "reviewInterval");
          final int _cursorIndexOfEasinessFactor = CursorUtil.getColumnIndexOrThrow(_cursor, "easinessFactor");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfLanguageCode = CursorUtil.getColumnIndexOrThrow(_cursor, "languageCode");
          final List<Word> _result = new ArrayList<Word>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Word _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpOriginalWord;
            if (_cursor.isNull(_cursorIndexOfOriginalWord)) {
              _tmpOriginalWord = null;
            } else {
              _tmpOriginalWord = _cursor.getString(_cursorIndexOfOriginalWord);
            }
            final String _tmpMeaning;
            if (_cursor.isNull(_cursorIndexOfMeaning)) {
              _tmpMeaning = null;
            } else {
              _tmpMeaning = _cursor.getString(_cursorIndexOfMeaning);
            }
            final String _tmpExampleSentence;
            if (_cursor.isNull(_cursorIndexOfExampleSentence)) {
              _tmpExampleSentence = null;
            } else {
              _tmpExampleSentence = _cursor.getString(_cursorIndexOfExampleSentence);
            }
            final long _tmpNextReviewDate;
            _tmpNextReviewDate = _cursor.getLong(_cursorIndexOfNextReviewDate);
            final int _tmpReviewInterval;
            _tmpReviewInterval = _cursor.getInt(_cursorIndexOfReviewInterval);
            final float _tmpEasinessFactor;
            _tmpEasinessFactor = _cursor.getFloat(_cursorIndexOfEasinessFactor);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final String _tmpLanguageCode;
            if (_cursor.isNull(_cursorIndexOfLanguageCode)) {
              _tmpLanguageCode = null;
            } else {
              _tmpLanguageCode = _cursor.getString(_cursorIndexOfLanguageCode);
            }
            _item = new Word(_tmpId,_tmpOriginalWord,_tmpMeaning,_tmpExampleSentence,_tmpNextReviewDate,_tmpReviewInterval,_tmpEasinessFactor,_tmpCategory,_tmpImageUrl,_tmpLanguageCode);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<String>> getAllCategories() {
    final String _sql = "SELECT DISTINCT category FROM words";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"words"}, new Callable<List<String>>() {
      @Override
      @NonNull
      public List<String> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final List<String> _result = new ArrayList<String>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final String _item;
            if (_cursor.isNull(0)) {
              _item = null;
            } else {
              _item = _cursor.getString(0);
            }
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<Word>> getWordsByCategory(final String category) {
    final String _sql = "SELECT * FROM words WHERE category = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (category == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, category);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"words"}, new Callable<List<Word>>() {
      @Override
      @NonNull
      public List<Word> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfOriginalWord = CursorUtil.getColumnIndexOrThrow(_cursor, "originalWord");
          final int _cursorIndexOfMeaning = CursorUtil.getColumnIndexOrThrow(_cursor, "meaning");
          final int _cursorIndexOfExampleSentence = CursorUtil.getColumnIndexOrThrow(_cursor, "exampleSentence");
          final int _cursorIndexOfNextReviewDate = CursorUtil.getColumnIndexOrThrow(_cursor, "nextReviewDate");
          final int _cursorIndexOfReviewInterval = CursorUtil.getColumnIndexOrThrow(_cursor, "reviewInterval");
          final int _cursorIndexOfEasinessFactor = CursorUtil.getColumnIndexOrThrow(_cursor, "easinessFactor");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfLanguageCode = CursorUtil.getColumnIndexOrThrow(_cursor, "languageCode");
          final List<Word> _result = new ArrayList<Word>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Word _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpOriginalWord;
            if (_cursor.isNull(_cursorIndexOfOriginalWord)) {
              _tmpOriginalWord = null;
            } else {
              _tmpOriginalWord = _cursor.getString(_cursorIndexOfOriginalWord);
            }
            final String _tmpMeaning;
            if (_cursor.isNull(_cursorIndexOfMeaning)) {
              _tmpMeaning = null;
            } else {
              _tmpMeaning = _cursor.getString(_cursorIndexOfMeaning);
            }
            final String _tmpExampleSentence;
            if (_cursor.isNull(_cursorIndexOfExampleSentence)) {
              _tmpExampleSentence = null;
            } else {
              _tmpExampleSentence = _cursor.getString(_cursorIndexOfExampleSentence);
            }
            final long _tmpNextReviewDate;
            _tmpNextReviewDate = _cursor.getLong(_cursorIndexOfNextReviewDate);
            final int _tmpReviewInterval;
            _tmpReviewInterval = _cursor.getInt(_cursorIndexOfReviewInterval);
            final float _tmpEasinessFactor;
            _tmpEasinessFactor = _cursor.getFloat(_cursorIndexOfEasinessFactor);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final String _tmpLanguageCode;
            if (_cursor.isNull(_cursorIndexOfLanguageCode)) {
              _tmpLanguageCode = null;
            } else {
              _tmpLanguageCode = _cursor.getString(_cursorIndexOfLanguageCode);
            }
            _item = new Word(_tmpId,_tmpOriginalWord,_tmpMeaning,_tmpExampleSentence,_tmpNextReviewDate,_tmpReviewInterval,_tmpEasinessFactor,_tmpCategory,_tmpImageUrl,_tmpLanguageCode);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getWordsToReviewByCategory(final String category, final long currentDate,
      final Continuation<? super List<Word>> $completion) {
    final String _sql = "SELECT * FROM words WHERE category = ? AND nextReviewDate <= ? ORDER BY nextReviewDate ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (category == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, category);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, currentDate);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Word>>() {
      @Override
      @NonNull
      public List<Word> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfOriginalWord = CursorUtil.getColumnIndexOrThrow(_cursor, "originalWord");
          final int _cursorIndexOfMeaning = CursorUtil.getColumnIndexOrThrow(_cursor, "meaning");
          final int _cursorIndexOfExampleSentence = CursorUtil.getColumnIndexOrThrow(_cursor, "exampleSentence");
          final int _cursorIndexOfNextReviewDate = CursorUtil.getColumnIndexOrThrow(_cursor, "nextReviewDate");
          final int _cursorIndexOfReviewInterval = CursorUtil.getColumnIndexOrThrow(_cursor, "reviewInterval");
          final int _cursorIndexOfEasinessFactor = CursorUtil.getColumnIndexOrThrow(_cursor, "easinessFactor");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfLanguageCode = CursorUtil.getColumnIndexOrThrow(_cursor, "languageCode");
          final List<Word> _result = new ArrayList<Word>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Word _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpOriginalWord;
            if (_cursor.isNull(_cursorIndexOfOriginalWord)) {
              _tmpOriginalWord = null;
            } else {
              _tmpOriginalWord = _cursor.getString(_cursorIndexOfOriginalWord);
            }
            final String _tmpMeaning;
            if (_cursor.isNull(_cursorIndexOfMeaning)) {
              _tmpMeaning = null;
            } else {
              _tmpMeaning = _cursor.getString(_cursorIndexOfMeaning);
            }
            final String _tmpExampleSentence;
            if (_cursor.isNull(_cursorIndexOfExampleSentence)) {
              _tmpExampleSentence = null;
            } else {
              _tmpExampleSentence = _cursor.getString(_cursorIndexOfExampleSentence);
            }
            final long _tmpNextReviewDate;
            _tmpNextReviewDate = _cursor.getLong(_cursorIndexOfNextReviewDate);
            final int _tmpReviewInterval;
            _tmpReviewInterval = _cursor.getInt(_cursorIndexOfReviewInterval);
            final float _tmpEasinessFactor;
            _tmpEasinessFactor = _cursor.getFloat(_cursorIndexOfEasinessFactor);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final String _tmpLanguageCode;
            if (_cursor.isNull(_cursorIndexOfLanguageCode)) {
              _tmpLanguageCode = null;
            } else {
              _tmpLanguageCode = _cursor.getString(_cursorIndexOfLanguageCode);
            }
            _item = new Word(_tmpId,_tmpOriginalWord,_tmpMeaning,_tmpExampleSentence,_tmpNextReviewDate,_tmpReviewInterval,_tmpEasinessFactor,_tmpCategory,_tmpImageUrl,_tmpLanguageCode);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
