// DO NOT EDIT
// Generated by JFlex 1.9.1 http://jflex.de/
// source: src/oberon.flex

import exceptions.*;


@SuppressWarnings("fallthrough")
public class OberonScanner {

  /** This character denotes the end of file. */
  public static final int YYEOF = -1;

  /** Initial size of the lookahead buffer. */
  private static final int ZZ_BUFFERSIZE = 16384;

  // Lexical states.
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = {
     0, 0
  };

  /**
   * Top-level table for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_TOP = zzUnpackcmap_top();

  private static final String ZZ_CMAP_TOP_PACKED_0 =
    "\1\0\1\u0100\36\u0200\1\u0300\267\u0200\10\u0400\u1020\u0200";

  private static int [] zzUnpackcmap_top() {
    int [] result = new int[4352];
    int offset = 0;
    offset = zzUnpackcmap_top(ZZ_CMAP_TOP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_top(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Second-level tables for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_BLOCKS = zzUnpackcmap_blocks();

  private static final String ZZ_CMAP_BLOCKS_PACKED_0 =
    "\11\0\1\1\1\2\1\3\1\1\1\4\22\0\1\1"+
    "\2\0\1\5\2\0\1\6\1\0\1\7\1\10\1\11"+
    "\1\12\1\13\1\14\1\15\1\0\1\16\7\17\2\20"+
    "\1\21\1\22\1\23\1\24\1\25\2\0\1\26\1\27"+
    "\1\30\1\31\1\32\1\33\1\34\1\35\1\36\2\37"+
    "\1\40\1\41\1\42\1\43\1\44\1\37\1\45\1\46"+
    "\1\47\1\50\1\51\1\52\1\37\1\53\1\37\1\54"+
    "\1\0\1\55\3\0\1\26\1\27\1\30\1\31\1\32"+
    "\1\33\1\34\1\35\1\36\2\37\1\40\1\41\1\42"+
    "\1\43\1\44\1\37\1\45\1\46\1\47\1\50\1\51"+
    "\1\52\1\37\1\53\1\37\1\0\1\56\1\0\1\57"+
    "\6\0\1\3\252\0\2\60\115\0\1\61\u01a8\0\2\3"+
    "\326\0\u0100\3";

  private static int [] zzUnpackcmap_blocks() {
    int [] result = new int[1280];
    int offset = 0;
    offset = zzUnpackcmap_blocks(ZZ_CMAP_BLOCKS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_blocks(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /**
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\3\1\2\1\4\1\5\1\6"+
    "\1\7\1\10\1\11\1\12\1\13\1\14\1\15\1\16"+
    "\1\17\1\20\1\21\1\22\1\23\16\24\1\25\1\26"+
    "\1\27\1\1\4\0\2\30\1\31\1\32\1\31\1\33"+
    "\1\34\1\35\5\24\1\36\1\0\2\24\1\37\2\24"+
    "\1\40\1\41\7\24\1\37\2\0\2\31\4\24\2\42"+
    "\1\24\1\0\1\43\1\24\1\44\5\24\1\45\1\24"+
    "\1\0\1\24\2\0\1\2\1\30\2\24\1\0\2\24"+
    "\1\0\1\46\1\24\1\0\1\46\3\24\1\47\1\24"+
    "\1\50\1\51\1\24\1\0\1\24\2\0\1\2\1\52"+
    "\2\53\1\24\2\54\2\55\4\24\2\56\2\57\1\0"+
    "\1\30\1\0\2\24\1\60\1\24\1\61\1\24\2\0"+
    "\1\30\1\62\1\63\1\24\2\64\1\63\1\24\1\65";

  private static int [] zzUnpackAction() {
    int [] result = new int[160];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\62\0\62\0\62\0\144\0\62\0\62\0\226"+
    "\0\62\0\310\0\62\0\62\0\62\0\62\0\372\0\u012c"+
    "\0\u015e\0\62\0\u0190\0\62\0\u01c2\0\u01f4\0\u0226\0\u0258"+
    "\0\u028a\0\u02bc\0\u02ee\0\u0320\0\u0352\0\u0384\0\u03b6\0\u03e8"+
    "\0\u041a\0\u044c\0\u047e\0\62\0\62\0\62\0\u04b0\0\62"+
    "\0\u04e2\0\310\0\u0514\0\u0546\0\62\0\u0578\0\u05aa\0\u05dc"+
    "\0\62\0\62\0\62\0\u060e\0\u0640\0\u0672\0\u06a4\0\u06d6"+
    "\0\u02ee\0\u0708\0\u073a\0\u076c\0\u02ee\0\u079e\0\u07d0\0\u02ee"+
    "\0\u02ee\0\u0802\0\u0834\0\u0866\0\u0898\0\u08ca\0\u08fc\0\u092e"+
    "\0\62\0\u0960\0\u0992\0\u09c4\0\u09f6\0\u0a28\0\u0a5a\0\u0a8c"+
    "\0\u0abe\0\u02ee\0\62\0\u0af0\0\u0b22\0\u02ee\0\u0b54\0\u0b86"+
    "\0\u0bb8\0\u0bea\0\u0c1c\0\u0c4e\0\u0c80\0\u02ee\0\u0cb2\0\u0ce4"+
    "\0\u0d16\0\u0d48\0\u0d7a\0\u0dac\0\u0dde\0\u0e10\0\u0e42\0\u0e74"+
    "\0\u0ea6\0\u0ed8\0\u0f0a\0\u02ee\0\u0f3c\0\u0f6e\0\62\0\u0fa0"+
    "\0\u0fd2\0\u1004\0\u02ee\0\u1036\0\u02ee\0\u02ee\0\u1068\0\u109a"+
    "\0\u10cc\0\u10fe\0\u1130\0\u1162\0\u02ee\0\u02ee\0\62\0\u1194"+
    "\0\u02ee\0\62\0\u02ee\0\62\0\u11c6\0\u11f8\0\u122a\0\u125c"+
    "\0\u02ee\0\62\0\u128e\0\u12c0\0\u12f2\0\u1162\0\u1324\0\u1356"+
    "\0\u1388\0\u02ee\0\u13ba\0\u02ee\0\u13ec\0\u141e\0\u1450\0\u1482"+
    "\0\u02ee\0\u02ee\0\u14b4\0\u02ee\0\62\0\62\0\u14e6\0\u02ee";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[160];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length() - 1;
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /**
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpacktrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\2\3\1\4\1\5\1\6\1\7\1\10\1\11"+
    "\1\12\1\13\1\14\1\15\1\16\1\17\2\20\1\21"+
    "\1\22\1\23\1\24\1\25\1\26\1\27\1\30\1\31"+
    "\1\32\3\33\1\34\2\33\1\35\1\33\1\36\1\37"+
    "\1\40\1\33\1\41\1\33\1\42\1\43\1\33\1\44"+
    "\1\45\1\2\1\46\1\47\1\2\7\50\1\51\1\50"+
    "\1\52\52\50\1\3\4\50\1\51\1\50\1\52\57\50"+
    "\1\53\1\50\1\54\57\50\1\51\1\55\1\52\57\50"+
    "\1\51\1\50\1\52\4\50\2\17\1\56\5\50\26\57"+
    "\2\50\1\60\12\50\1\51\1\50\1\52\4\50\3\20"+
    "\5\50\26\57\15\50\1\51\1\50\1\52\12\50\1\61"+
    "\44\50\1\51\1\50\1\52\12\50\1\62\44\50\1\51"+
    "\1\50\1\52\12\50\1\63\44\50\1\51\1\50\1\52"+
    "\4\50\3\33\5\50\17\33\1\64\6\33\2\50\1\33"+
    "\12\50\1\51\1\50\1\52\4\50\3\33\5\50\4\33"+
    "\1\65\10\33\1\66\10\33\2\50\1\33\12\50\1\51"+
    "\1\50\1\52\4\50\3\33\5\50\15\33\1\67\10\33"+
    "\2\50\1\33\12\50\1\51\1\50\1\52\4\50\3\33"+
    "\5\50\10\33\1\70\4\33\1\71\10\33\2\50\1\33"+
    "\1\50\1\72\10\50\1\51\1\50\1\52\4\50\3\33"+
    "\5\50\12\33\1\73\1\33\1\74\11\33\2\50\1\33"+
    "\12\50\1\51\1\50\1\52\4\50\3\33\5\50\26\33"+
    "\2\50\1\33\12\50\1\51\1\50\1\52\4\50\3\33"+
    "\5\50\5\33\1\75\6\33\1\76\11\33\2\50\1\33"+
    "\12\50\1\51\1\50\1\52\4\50\3\33\5\50\15\33"+
    "\1\77\10\33\2\50\1\33\12\50\1\51\1\50\1\52"+
    "\4\50\3\33\5\50\5\33\1\100\11\33\1\101\6\33"+
    "\2\50\1\33\12\50\1\51\1\50\1\52\4\50\3\33"+
    "\5\50\17\33\1\102\6\33\2\50\1\33\12\50\1\51"+
    "\1\50\1\52\4\50\3\33\5\50\4\33\1\103\21\33"+
    "\2\50\1\33\12\50\1\51\1\50\1\52\4\50\3\33"+
    "\5\50\7\33\1\104\15\33\1\105\2\50\1\33\12\50"+
    "\1\51\1\50\1\52\4\50\3\33\5\50\1\106\25\33"+
    "\2\50\1\33\12\50\1\51\1\50\1\52\4\50\3\33"+
    "\5\50\7\33\1\107\7\33\1\110\6\33\2\50\1\33"+
    "\12\50\1\51\1\50\1\52\21\50\1\111\6\50\1\112"+
    "\26\50\1\53\1\50\1\0\57\50\1\53\1\50\1\52"+
    "\50\50\11\54\1\113\50\54\7\50\1\51\1\50\1\52"+
    "\4\50\2\114\1\56\5\50\26\57\2\50\1\60\12\50"+
    "\1\51\1\50\1\52\4\50\3\57\5\50\26\57\2\50"+
    "\1\57\12\50\1\51\1\50\1\52\4\50\2\115\1\60"+
    "\35\50\1\60\12\50\1\51\1\50\1\52\4\50\3\33"+
    "\5\50\17\33\1\116\6\33\2\50\1\33\12\50\1\51"+
    "\1\50\1\52\4\50\3\33\5\50\6\33\1\117\17\33"+
    "\2\50\1\33\12\50\1\51\1\50\1\52\4\50\3\33"+
    "\5\50\15\33\1\120\10\33\2\50\1\33\12\50\1\51"+
    "\1\50\1\52\4\50\3\33\5\50\14\33\1\121\11\33"+
    "\2\50\1\33\12\50\1\51\1\50\1\52\4\50\3\33"+
    "\5\50\23\33\1\122\2\33\2\50\1\33\12\50\1\51"+
    "\1\50\1\52\37\50\1\123\17\50\1\51\1\50\1\52"+
    "\4\50\3\33\5\50\20\33\1\124\5\33\2\50\1\33"+
    "\2\50\1\125\7\50\1\51\1\50\1\52\4\50\3\33"+
    "\5\50\3\33\1\126\22\33\2\50\1\33\12\50\1\51"+
    "\1\50\1\52\4\50\3\33\5\50\21\33\1\127\4\33"+
    "\2\50\1\33\12\50\1\51\1\50\1\52\4\50\3\33"+
    "\5\50\3\33\1\130\22\33\2\50\1\33\12\50\1\51"+
    "\1\50\1\52\4\50\3\33\5\50\15\33\1\131\10\33"+
    "\2\50\1\33\12\50\1\51\1\50\1\52\4\50\3\33"+
    "\5\50\1\132\1\33\1\133\23\33\2\50\1\33\12\50"+
    "\1\51\1\50\1\52\4\50\3\33\5\50\4\33\1\134"+
    "\21\33\2\50\1\33\12\50\1\51\1\50\1\52\4\50"+
    "\3\33\5\50\16\33\1\135\7\33\2\50\1\33\12\50"+
    "\1\51\1\50\1\52\4\50\3\33\5\50\17\33\1\136"+
    "\6\33\2\50\1\33\12\50\1\51\1\50\1\52\4\50"+
    "\3\33\5\50\10\33\1\137\15\33\2\50\1\33\1\50"+
    "\1\140\10\50\1\51\1\50\1\52\4\50\3\33\5\50"+
    "\10\33\1\141\15\33\2\50\1\33\1\50\1\142\10\50"+
    "\1\51\1\50\1\52\35\50\1\143\12\50\10\54\1\144"+
    "\1\145\50\54\7\50\1\51\1\50\1\52\4\50\3\114"+
    "\5\50\26\57\15\50\1\51\1\50\1\52\4\50\3\115"+
    "\50\50\1\51\1\50\1\52\4\50\3\33\5\50\1\146"+
    "\25\33\2\50\1\33\12\50\1\51\1\50\1\52\4\50"+
    "\3\33\5\50\10\33\1\147\15\33\2\50\1\33\1\50"+
    "\1\150\10\50\1\51\1\50\1\52\4\50\3\33\5\50"+
    "\12\33\1\151\13\33\2\50\1\33\12\50\1\51\1\50"+
    "\1\52\4\50\3\33\5\50\20\33\1\152\5\33\2\50"+
    "\1\33\2\50\1\153\7\50\1\51\1\50\1\52\4\50"+
    "\3\33\5\50\4\33\1\154\3\33\1\155\15\33\2\50"+
    "\1\33\1\50\1\156\10\50\1\51\1\50\1\52\20\50"+
    "\1\157\3\50\1\156\21\50\1\156\10\50\1\51\1\50"+
    "\1\52\4\50\3\33\5\50\4\33\1\160\21\33\2\50"+
    "\1\33\12\50\1\51\1\50\1\52\4\50\3\33\5\50"+
    "\22\33\1\161\3\33\2\50\1\33\12\50\1\51\1\50"+
    "\1\52\4\50\3\33\5\50\2\33\1\162\23\33\2\50"+
    "\1\33\12\50\1\51\1\50\1\52\4\50\3\33\5\50"+
    "\3\33\1\163\22\33\2\50\1\33\12\50\1\51\1\50"+
    "\1\52\4\50\3\33\5\50\15\33\1\164\10\33\2\50"+
    "\1\33\12\50\1\51\1\50\1\52\4\50\3\33\5\50"+
    "\14\33\1\165\11\33\2\50\1\33\12\50\1\51\1\50"+
    "\1\52\4\50\3\33\5\50\4\33\1\166\21\33\2\50"+
    "\1\33\12\50\1\51\1\50\1\52\4\50\3\33\5\50"+
    "\12\33\1\167\13\33\2\50\1\33\12\50\1\51\1\50"+
    "\1\52\26\50\1\170\30\50\1\51\1\50\1\52\4\50"+
    "\3\33\5\50\21\33\1\171\4\33\2\50\1\33\12\50"+
    "\1\51\1\50\1\52\35\50\1\172\21\50\1\51\1\50"+
    "\1\52\20\50\1\173\27\50\62\0\10\54\1\174\1\145"+
    "\50\54\7\50\1\51\1\50\1\52\4\50\3\33\5\50"+
    "\25\33\1\175\2\50\1\33\12\50\1\51\1\50\1\52"+
    "\4\50\3\33\5\50\14\33\1\176\11\33\2\50\1\33"+
    "\12\50\1\51\1\50\1\52\30\50\1\177\26\50\1\51"+
    "\1\50\1\52\4\50\3\33\5\50\4\33\1\200\21\33"+
    "\2\50\1\33\12\50\1\51\1\50\1\52\4\50\3\33"+
    "\5\50\21\33\1\201\4\33\2\50\1\33\12\50\1\51"+
    "\1\50\1\52\35\50\1\202\21\50\1\51\1\50\1\52"+
    "\4\50\3\33\5\50\5\33\1\203\20\33\2\50\1\33"+
    "\12\50\1\51\1\50\1\52\21\50\1\204\35\50\1\51"+
    "\1\50\1\52\4\50\3\33\5\50\6\33\1\205\17\33"+
    "\2\50\1\33\12\50\1\51\1\50\1\52\4\50\3\33"+
    "\5\50\12\33\1\206\13\33\2\50\1\33\12\50\1\51"+
    "\1\50\1\52\4\50\3\33\5\50\4\33\1\207\21\33"+
    "\2\50\1\33\12\50\1\51\1\50\1\52\4\50\3\33"+
    "\5\50\17\33\1\210\6\33\2\50\1\33\12\50\1\51"+
    "\1\50\1\52\4\50\3\33\5\50\4\33\1\211\21\33"+
    "\2\50\1\33\12\50\1\51\1\50\1\52\20\50\1\212"+
    "\36\50\1\51\1\50\1\52\4\50\3\33\5\50\4\33"+
    "\1\213\21\33\2\50\1\33\12\50\1\51\1\50\1\52"+
    "\20\50\1\214\36\50\1\51\1\50\1\52\22\50\1\215"+
    "\25\50\11\216\1\217\50\216\7\50\1\51\1\50\1\52"+
    "\4\50\3\33\5\50\1\220\25\33\2\50\1\33\12\50"+
    "\1\51\1\50\1\52\4\50\3\33\5\50\4\33\1\221"+
    "\21\33\2\50\1\33\12\50\1\51\1\50\1\52\4\50"+
    "\3\33\5\50\4\33\1\222\21\33\2\50\1\33\12\50"+
    "\1\51\1\50\1\52\4\50\3\33\5\50\3\33\1\223"+
    "\22\33\2\50\1\33\12\50\1\51\1\50\1\52\4\50"+
    "\3\33\5\50\3\33\1\224\22\33\2\50\1\33\12\50"+
    "\1\51\1\50\1\52\4\50\3\33\5\50\12\33\1\225"+
    "\13\33\2\50\1\33\12\50\1\51\1\50\1\52\26\50"+
    "\1\226\30\50\1\51\1\50\1\52\20\50\1\227\27\50"+
    "\10\216\1\0\1\230\50\216\7\50\1\51\1\50\1\52"+
    "\4\50\3\33\5\50\14\33\1\231\11\33\2\50\1\33"+
    "\12\50\1\51\1\50\1\52\4\50\3\33\5\50\17\33"+
    "\1\232\6\33\2\50\1\33\12\50\1\51\1\50\1\52"+
    "\4\50\3\33\5\50\22\33\1\233\3\33\2\50\1\33"+
    "\12\50\1\51\1\50\1\52\4\50\3\33\5\50\14\33"+
    "\1\234\11\33\2\50\1\33\12\50\1\51\1\50\1\52"+
    "\30\50\1\235\26\50\1\51\1\50\1\52\33\50\1\236"+
    "\14\50\11\216\1\230\50\216\7\50\1\51\1\50\1\52"+
    "\4\50\3\33\5\50\17\33\1\237\6\33\2\50\1\33"+
    "\12\50\1\51\1\50\1\52\4\50\3\33\5\50\4\33"+
    "\1\240\21\33\2\50\1\33\3\50";

  private static int [] zzUnpacktrans() {
    int [] result = new int[5400];
    int offset = 0;
    offset = zzUnpacktrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpacktrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** Error code for "Unknown internal scanner error". */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  /** Error code for "could not match input". */
  private static final int ZZ_NO_MATCH = 1;
  /** Error code for "pushback value was too large". */
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /**
   * Error messages for {@link #ZZ_UNKNOWN_ERROR}, {@link #ZZ_NO_MATCH}, and
   * {@link #ZZ_PUSHBACK_2BIG} respectively.
   */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state {@code aState}
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\46\1\4\0\16\1\1\0\17\1\2\0\11\1"+
    "\1\0\12\1\1\0\1\1\2\0\1\11\3\1\1\0"+
    "\2\1\1\0\2\1\1\0\11\1\1\0\1\1\2\0"+
    "\21\1\1\0\1\1\1\0\6\1\2\0\11\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[160];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** Input device. */
  private java.io.Reader zzReader;

  /** Current state of the DFA. */
  private int zzState;

  /** Current lexical state. */
  private int zzLexicalState = YYINITIAL;

  /**
   * This buffer contains the current text to be matched and is the source of the {@link #yytext()}
   * string.
   */
  private char zzBuffer[] = new char[Math.min(ZZ_BUFFERSIZE, zzMaxBufferLen())];

  /** Text position at the last accepting state. */
  private int zzMarkedPos;

  /** Current text position in the buffer. */
  private int zzCurrentPos;

  /** Marks the beginning of the {@link #yytext()} string in the buffer. */
  private int zzStartRead;

  /** Marks the last character in the buffer, that has been read from input. */
  private int zzEndRead;

  /**
   * Whether the scanner is at the end of file.
   * @see #yyatEOF
   */
  private boolean zzAtEOF;

  /**
   * The number of occupied positions in {@link #zzBuffer} beyond {@link #zzEndRead}.
   *
   * <p>When a lead/high surrogate has been read from the input stream into the final
   * {@link #zzBuffer} position, this will have a value of 1; otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /** Number of newlines encountered up to the start of the matched text. */
  private int yyline;

  /** Number of characters from the last newline up to the start of the matched text. */
  private int yycolumn;

  /** Number of characters up to the start of the matched text. */
  @SuppressWarnings("unused")
  private long yychar;

  /** Whether the scanner is currently at the beginning of a line. */
  @SuppressWarnings("unused")
  private boolean zzAtBOL = true;

  /** Whether the user-EOF-code has already been executed. */
  @SuppressWarnings("unused")
  private boolean zzEOFDone;

  /* user code: */
    public int getLine() {
        return yyline;
    }

    public int getCol() {
        return yycolumn;
    }

    public String getPos() {
        return String.format("<%d:%d>", getLine() + 1, getCol() + 1);
    }


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public OberonScanner(java.io.Reader in) {
    this.zzReader = in;
  }


  /** Returns the maximum size of the scanner buffer, which limits the size of tokens. */
  private int zzMaxBufferLen() {
    return Integer.MAX_VALUE;
  }

  /**  Whether the scanner buffer can grow to accommodate a larger token. */
  private boolean zzCanGrow() {
    return true;
  }

  /**
   * Translates raw input code points to DFA table row
   */
  private static int zzCMap(int input) {
    int offset = input & 255;
    return offset == input ? ZZ_CMAP_BLOCKS[offset] : ZZ_CMAP_BLOCKS[ZZ_CMAP_TOP[input >> 8] | offset];
  }

  /**
   * Refills the input buffer.
   *
   * @return {@code false} iff there was new input.
   * @exception java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead - zzStartRead);

      /* translate stored positions */
      zzEndRead -= zzStartRead;
      zzCurrentPos -= zzStartRead;
      zzMarkedPos -= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate && zzCanGrow()) {
      /* if not, and it can grow: blow it up */
      char newBuffer[] = new char[Math.min(zzBuffer.length * 2, zzMaxBufferLen())];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      if (requested == 0) {
        throw new java.io.EOFException("Scan buffer limit reached ["+zzBuffer.length+"]");
      }
      else {
        throw new java.io.IOException(
            "Reader returned 0 characters. See JFlex examples/zero-reader for a workaround.");
      }
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
        if (numRead == requested) { // We requested too few chars to encode a full Unicode character
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        } else {                    // There is room in the buffer for at least one more char
          int c = zzReader.read();  // Expecting to read a paired low surrogate char
          if (c == -1) {
            return true;
          } else {
            zzBuffer[zzEndRead++] = (char)c;
          }
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }


  /**
   * Closes the input reader.
   *
   * @throws java.io.IOException if the reader could not be closed.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true; // indicate end of file
    zzEndRead = zzStartRead; // invalidate buffer

    if (zzReader != null) {
      zzReader.close();
    }
  }


  /**
   * Resets the scanner to read from a new input stream.
   *
   * <p>Does not close the old reader.
   *
   * <p>All internal variables are reset, the old input stream <b>cannot</b> be reused (internal
   * buffer is discarded and lost). Lexical state is set to {@code ZZ_INITIAL}.
   *
   * <p>Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader The new input stream.
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzEOFDone = false;
    yyResetPosition();
    zzLexicalState = YYINITIAL;
    int initBufferSize = Math.min(ZZ_BUFFERSIZE, zzMaxBufferLen());
    if (zzBuffer.length > initBufferSize) {
      zzBuffer = new char[initBufferSize];
    }
  }

  /**
   * Resets the input position.
   */
  private final void yyResetPosition() {
      zzAtBOL  = true;
      zzAtEOF  = false;
      zzCurrentPos = 0;
      zzMarkedPos = 0;
      zzStartRead = 0;
      zzEndRead = 0;
      zzFinalHighSurrogate = 0;
      yyline = 0;
      yycolumn = 0;
      yychar = 0L;
  }


  /**
   * Returns whether the scanner has reached the end of the reader it reads from.
   *
   * @return whether the scanner has reached EOF.
   */
  public final boolean yyatEOF() {
    return zzAtEOF;
  }


  /**
   * Returns the current lexical state.
   *
   * @return the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state.
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   *
   * @return the matched text.
   */
  public final String yytext() {
    return new String(zzBuffer, zzStartRead, zzMarkedPos-zzStartRead);
  }


  /**
   * Returns the character at the given position from the matched text.
   *
   * <p>It is equivalent to {@code yytext().charAt(pos)}, but faster.
   *
   * @param position the position of the character to fetch. A value from 0 to {@code yylength()-1}.
   *
   * @return the character at {@code position}.
   */
  public final char yycharat(int position) {
    return zzBuffer[zzStartRead + position];
  }


  /**
   * How many characters were matched.
   *
   * @return the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occurred while scanning.
   *
   * <p>In a well-formed scanner (no or only correct usage of {@code yypushback(int)} and a
   * match-all fallback rule) this method will only be called with things that
   * "Can't Possibly Happen".
   *
   * <p>If this method is called, something is seriously wrong (e.g. a JFlex bug producing a faulty
   * scanner etc.).
   *
   * <p>Usual syntax/scanner level error handling should be done in error fallback rules.
   *
   * @param errorCode the code of the error message to display.
   */
  private static void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    } catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * <p>They will be read again by then next call of the scanning method.
   *
   * @param number the number of characters to be read again. This number must not be greater than
   *     {@link #yylength()}.
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }




  /**
   * Resumes scanning until the next regular expression is matched, the end of input is encountered
   * or an I/O-Error occurs.
   *
   * @return the next token.
   * @exception java.io.IOException if any I/O-Error occurs.
   */
  public Token yylex() throws java.io.IOException
    , LexicalException
  {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char[] zzBufferL = zzBuffer;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':  // fall through
        case '\u000C':  // fall through
        case '\u0085':  // fall through
        case '\u2028':  // fall through
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is
        // (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof)
            zzPeek = false;
          else
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMap(zzInput) ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
          {     return new Token(TokenType.tok_eof);
 }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1:
            { throw new IllegalSymbolException();
            }
          // fall through
          case 54: break;
          case 2:
            { 
            }
          // fall through
          case 55: break;
          case 3:
            { System.out.print(yytext());
            }
          // fall through
          case 56: break;
          case 4:
            { return new Token(TokenType.tok_not_equal, "&ne");
            }
          // fall through
          case 57: break;
          case 5:
            { return new Token(TokenType.tok_and, "&");
            }
          // fall through
          case 58: break;
          case 6:
            { return new Token(TokenType.tok_lparen);
            }
          // fall through
          case 59: break;
          case 7:
            { return new Token(TokenType.tok_rparen);
            }
          // fall through
          case 60: break;
          case 8:
            { return new Token(TokenType.tok_multiply, "*");
            }
          // fall through
          case 61: break;
          case 9:
            { return new Token(TokenType.tok_plus, "+");
            }
          // fall through
          case 62: break;
          case 10:
            { return new Token(TokenType.tok_comma);
            }
          // fall through
          case 63: break;
          case 11:
            { return new Token(TokenType.tok_minus, "-");
            }
          // fall through
          case 64: break;
          case 12:
            { return new Token(TokenType.tok_dot);
            }
          // fall through
          case 65: break;
          case 13:
            { if (yylength() > 12)
        throw new IllegalIntegerRangeException();
    return new Token(TokenType.tok_number, Integer.parseInt(yytext(), 8));
            }
          // fall through
          case 66: break;
          case 14:
            { if (yylength() > 12)
        throw new IllegalIntegerRangeException();
    return new Token(TokenType.tok_number, Integer.parseInt(yytext()));
            }
          // fall through
          case 67: break;
          case 15:
            { return new Token(TokenType.tok_colon);
            }
          // fall through
          case 68: break;
          case 16:
            { return new Token(TokenType.tok_semicolon);
            }
          // fall through
          case 69: break;
          case 17:
            { return new Token(TokenType.tok_less, "&lt");
            }
          // fall through
          case 70: break;
          case 18:
            { return new Token(TokenType.tok_equal, "=");
            }
          // fall through
          case 71: break;
          case 19:
            { return new Token(TokenType.tok_greater, "&gt");
            }
          // fall through
          case 72: break;
          case 20:
            { if (yylength() > 24)
        throw new IllegalIdentifierLengthException();
    return new Token(TokenType.tok_identifier, yytext().toUpperCase());
            }
          // fall through
          case 73: break;
          case 21:
            { return new Token(TokenType.tok_lbracket);
            }
          // fall through
          case 74: break;
          case 22:
            { return new Token(TokenType.tok_rbracket);
            }
          // fall through
          case 75: break;
          case 23:
            { return new Token(TokenType.tok_not, "~");
            }
          // fall through
          case 76: break;
          case 24:
            { throw new MismatchedCommentException();
            }
          // fall through
          case 77: break;
          case 25:
            { throw new IllegalOctalException();
            }
          // fall through
          case 78: break;
          case 26:
            { throw new IllegalIntegerException();
            }
          // fall through
          case 79: break;
          case 27:
            { return new Token(TokenType.tok_assign);
            }
          // fall through
          case 80: break;
          case 28:
            { return new Token(TokenType.tok_less_equal, "&le");
            }
          // fall through
          case 81: break;
          case 29:
            { return new Token(TokenType.tok_greater_equal, "&ge");
            }
          // fall through
          case 82: break;
          case 30:
            { return new Token(TokenType.tok_do);
            }
          // fall through
          case 83: break;
          case 31:
            { return new Token(TokenType.tok_if);
            }
          // fall through
          case 84: break;
          case 32:
            { return new Token(TokenType.tok_of);
            }
          // fall through
          case 85: break;
          case 33:
            { return new Token(TokenType.tok_or, "OR");
            }
          // fall through
          case 86: break;
          case 34:
            { return new Token(TokenType.tok_divide, "DIV");
            }
          // fall through
          case 87: break;
          case 35:
            { return new Token(TokenType.tok_end);
            }
          // fall through
          case 88: break;
          case 36:
            { return new Token(TokenType.tok_mod, "MOD");
            }
          // fall through
          case 89: break;
          case 37:
            { return new Token(TokenType.tok_var);
            }
          // fall through
          case 90: break;
          case 38:
            { return new Token(TokenType.tok_else);
            }
          // fall through
          case 91: break;
          case 39:
            { return new Token(TokenType.tok_read, "READ");
            }
          // fall through
          case 92: break;
          case 40:
            { return new Token(TokenType.tok_then);
            }
          // fall through
          case 93: break;
          case 41:
            { return new Token(TokenType.tok_type);
            }
          // fall through
          case 94: break;
          case 42:
            { return new Token(TokenType.tok_array);
            }
          // fall through
          case 95: break;
          case 43:
            { return new Token(TokenType.tok_begin);
            }
          // fall through
          case 96: break;
          case 44:
            { return new Token(TokenType.tok_const);
            }
          // fall through
          case 97: break;
          case 45:
            { return new Token(TokenType.tok_elsif);
            }
          // fall through
          case 98: break;
          case 46:
            { return new Token(TokenType.tok_while);
            }
          // fall through
          case 99: break;
          case 47:
            { return new Token(TokenType.tok_write, "WRITE");
            }
          // fall through
          case 100: break;
          case 48:
            { return new Token(TokenType.tok_module);
            }
          // fall through
          case 101: break;
          case 49:
            { return new Token(TokenType.tok_record);
            }
          // fall through
          case 102: break;
          case 50:
            { return new Token(TokenType.tok_boolean);
            }
          // fall through
          case 103: break;
          case 51:
            { return new Token(TokenType.tok_integer);
            }
          // fall through
          case 104: break;
          case 52:
            { return new Token(TokenType.tok_writeln, "WRITELN");
            }
          // fall through
          case 105: break;
          case 53:
            { return new Token(TokenType.tok_procedure);
            }
          // fall through
          case 106: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }

  /**
   * Runs the scanner on input files.
   *
   * This is a standalone scanner, it will print any unmatched
   * text to System.out unchanged.
   *
   * @param argv   the command line, contains the filenames to run
   *               the scanner on.
   */
  public static void main(String[] argv) {
    if (argv.length == 0) {
      System.out.println("Usage : java OberonScanner [ --encoding <name> ] <inputfile(s)>");
    }
    else {
      int firstFilePos = 0;
      String encodingName = "UTF-8";
      if (argv[0].equals("--encoding")) {
        firstFilePos = 2;
        encodingName = argv[1];
        try {
          // Side-effect: is encodingName valid?
          java.nio.charset.Charset.forName(encodingName);
        } catch (Exception e) {
          System.out.println("Invalid encoding '" + encodingName + "'");
          return;
        }
      }
      for (int i = firstFilePos; i < argv.length; i++) {
        OberonScanner scanner = null;
        java.io.FileInputStream stream = null;
        java.io.Reader reader = null;
        try {
          stream = new java.io.FileInputStream(argv[i]);
          reader = new java.io.InputStreamReader(stream, encodingName);
          scanner = new OberonScanner(reader);
          while ( !scanner.zzAtEOF ) scanner.yylex();
        }
        catch (java.io.FileNotFoundException e) {
          System.out.println("File not found : \""+argv[i]+"\"");
        }
        catch (java.io.IOException e) {
          System.out.println("IO error scanning file \""+argv[i]+"\"");
          System.out.println(e);
        }
        catch (Exception e) {
          System.out.println("Unexpected exception:");
          e.printStackTrace();
        }
        finally {
          if (reader != null) {
            try {
              reader.close();
            }
            catch (java.io.IOException e) {
              System.out.println("IO error closing file \""+argv[i]+"\"");
              System.out.println(e);
            }
          }
          if (stream != null) {
            try {
              stream.close();
            }
            catch (java.io.IOException e) {
              System.out.println("IO error closing file \""+argv[i]+"\"");
              System.out.println(e);
            }
          }
        }
      }
    }
  }


}
