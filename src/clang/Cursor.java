package clang;

//import java.nio.IntBuffer;
import clang.binding.ClangLibrary;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

import static clang.binding.ClangLibrary.*;

public class Cursor 
implements
	Comparable<Cursor>,
	CXCursorKind,
	CXAvailabilityKind, 
	CXLinkageKind, 
	CXLanguageKind,
	CX_CXXAccessSpecifier
{
	void echo(String s) { System.out.println(s); }
	
	
	private CXCursor.ByValue cxcursor;
	CXCursor.ByValue cxcursor() { return cxcursor; }

	Cursor(CXCursor.ByValue cursor) {
		this.cxcursor = cursor;
	}
	
	/**
	 * @see clang.binding.ClangLibrary#clang_getNullCursor()
	 */
	public static Cursor getNullCursor() {
		return new Cursor(clang_getNullCursor());
	}

	
	// TODO doesn't work
	int semanticDepth() { 
		if (isTranslationUnit()) { return 0; }
		if (isInvalid()) { /*echo("invalid");*/ return 0; }
		//if (isUnexposed()) { echo("unexposed"); return 0; }
		Cursor parent = getSemanticParent();
		if (parent == null) return 0;
		return parent.semanticDepth() + 1;
	}
	// TODO doesn't work
	int lexicalDepth() { 
		if (isTranslationUnit()) return 0;
		if (isInvalid()) { /*echo("invalid");*/ return 0; }
		//if (isUnexposed()) { echo("unexposed"); return 0; }
		Cursor parent = getLexicalParent();
		if (parent == null) return 0;
		return parent.lexicalDepth() + 1;
	}
	
	
	@Override
	public String toString() {
		//if (isTranslationUnit()) return "TU";
		//if (isDeclaration()) return getDisplayName();
		if (isExpression()) return getDisplayName();
		return getSpelling();
	}

	@Override
	public int hashCode() {
		return 31 + hashCursor();
	}
	
	/**
	 * @see clang.binding.ClangLibrary#clang_hashCursor(clang.CXCursor.ByValue)
	 */
	public int hashCursor() {
		if (cxcursor == null) return 0;
		return clang_hashCursor(cxcursor);				
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof Cursor)) return false;
		Cursor other = (Cursor) obj;
		if (cxcursor == null) {
			if (other.cxcursor != null) {
				return false;
			}
		} else if (!equalCursors(other)) {
			return false;
		}
		return true;
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_equalCursors(clang.binding.ClangLibrary.CXCursor.ByValue, clang.binding.ClangLibrary.CXCursor.ByValue)
	 */
	public boolean equalCursors(Cursor other) {
		return clang_equalCursors(cxcursor(), other.cxcursor()) != 0;
	}

	@Override
	public int compareTo(Cursor other) { 
		if (other == null) return 1;
		if (isInvalid())  return other.isInvalid() ? 0 : -1;
		if (other.isInvalid()) return 1;

		return getLocation().compareTo(other.getLocation());
	}


	/**
	 * @see clang.binding.ClangLibrary#clang_getCursorKind(clang.binding.ClangLibrary.CXCursor.ByValue)
	 */
	int getCursorKind() {
		// TODO enumerate
		return clang_getCursorKind(cxcursor);  // same as cxcursor().kind  ??
	}

	public boolean       isStructDecl() { return getCursorKind() == CXCursor_StructDecl; }
	public boolean        isUnionDecl() { return getCursorKind() == CXCursor_UnionDecl; }
	public boolean        isClassDecl() { return getCursorKind() == CXCursor_ClassDecl; }
	public boolean         isEnumDecl() { return getCursorKind() == CXCursor_EnumDecl; }
	public boolean        isFieldDecl() { return getCursorKind() == CXCursor_FieldDecl; }
	public boolean isEnumConstantDecl() { return getCursorKind() == CXCursor_EnumConstantDecl; }
	public boolean     isFunctionDecl() { return getCursorKind() == CXCursor_FunctionDecl; }
	public boolean          isVarDecl() { return getCursorKind() == CXCursor_VarDecl; }
	public boolean         isParmDecl() { return getCursorKind() == CXCursor_ParmDecl; }
	
	
	/**
	 * @see clang.binding.ClangLibrary#clang_isDeclaration(int)
	 */
	public boolean isDeclaration() { return clang_isDeclaration(cxcursor().kind) != 0; }

	/**
	 * @see clang.binding.ClangLibrary#clang_isReference(int)
	 */
	public boolean isReference() { return clang_isReference(cxcursor().kind) != 0; }

	/**
	 * @see clang.binding.ClangLibrary#clang_isExpression(int)
	 */
	public boolean isExpression() { return clang_isExpression(cxcursor().kind) != 0; }

	/**
	 * @see clang.binding.ClangLibrary#clang_isStatement(int)
	 */
	public boolean isStatement() { return clang_isStatement(cxcursor().kind) != 0; }

	/**
	 * @see clang.binding.ClangLibrary#clang_isInvalid(int)
	 */
	public boolean isInvalid() { return clang_isInvalid(cxcursor().kind) != 0; }

	/**
	 * @see clang.binding.ClangLibrary#clang_isTranslationUnit(int)
	 */
	public boolean isTranslationUnit() { return clang_isTranslationUnit(cxcursor().kind) != 0; }

	
	/**
	 * @see clang.binding.ClangLibrary#clang_isPreprocessing(int)
	 */
	public boolean isPreprocessing() { return clang_isPreprocessing(cxcursor().kind) != 0; }

	/**
	 * @see clang.binding.ClangLibrary#clang_isUnexposed(int)
	 */
	public boolean isUnexposed() { return clang_isUnexposed(cxcursor().kind) != 0; }

	
	/**
	 * @see clang.binding.ClangLibrary.CXLinkageKind
	 * @see clang.binding.ClangLibrary#clang_getCursorLinkage(clang.binding.ClangLibrary.CXCursor.ByValue)
	 */
	int getLinkage() { return clang_getCursorLinkage(cxcursor()); }
	/**
	 * \brief This value indicates that no linkage information is available<br>
	 * for a provided CXCursor.<br>
	 * <i>native declaration : clang-c\Index.h:1021</i>
	 */
	public boolean isLinkageInvalid() { return getLinkage() == CXLinkage_Invalid; }
	/**
	 * \brief This is the linkage for variables, parameters, and so on that<br>
	 *  have automatic storage.  This covers normal (non-extern) local variables.<br>
	 * <i>native declaration : clang-c\Index.h:1026</i>
	 */
	public boolean isLinkageNoLinkage() { return getLinkage() == CXLinkage_NoLinkage; }
	/**
	 * \brief This is the linkage for static variables and static functions.<br>
	 * <i>native declaration : clang-c\Index.h:1028</i>
	 */
	public boolean isLinkageInternal() { return getLinkage() == CXLinkage_Internal; }
	/**
	 * \brief This is the linkage for entities with external linkage that live<br>
	 * in C++ anonymous namespaces.<br>
	 * <i>native declaration : clang-c\Index.h:1033</i>
	 */
	public boolean isLinkageUniqueExternal() { return getLinkage() == CXLinkage_UniqueExternal; }
	/**
	 * \brief This is the linkage for entities with true, external linkage.<br>
	 * <i>native declaration : clang-c\Index.h:1035</i>
	 */
	public boolean isLinkageExternal() { return getLinkage() == CXLinkage_External; }
	
	
	/**
	 * @see clang.binding.ClangLibrary.CXAvailabilityKind
	 * @see clang.binding.ClangLibrary#clang_getCursorAvailability(clang.binding.ClangLibrary.CXCursor.ByValue)
	 */
	int getAvailability() { return clang_getCursorAvailability(cxcursor()); }
	/**
	 * \brief The entity is available.<br>
	 * <i>native declaration : clang-c\Index.h:32</i>
	 */
	public boolean isAvailable() { return getAvailability() == CXAvailability_Available; }
	/**
	 * \brief The entity is available, but has been deprecated (and its use is<br>
	 * not recommended).<br>
	 * <i>native declaration : clang-c\Index.h:37</i>
	 */
	public boolean isDeprecated() { return getAvailability() == CXAvailability_Deprecated; }
	/**
	 * \brief The entity is not available; any use of it will be an error.<br>
	 * <i>native declaration : clang-c\Index.h:39</i>
	 */
	public boolean isNotAvailable() { return getAvailability() == CXAvailability_NotAvailable; }

	
	/**
	 * @see clang.binding.ClangLibrary.CXLanguageKind
	 * @see clang.binding.ClangLibrary#clang_getCursorLanguage(clang.binding.ClangLibrary.CXCursor.ByValue)
	 */
	int getLanguage() { return clang_getCursorLanguage(cxcursor()); }
	public boolean isLanguageInvalid() { return getLanguage() == CXLanguage_Invalid; }
	public boolean isLanguageC() { return getLanguage() == CXLanguage_C; }
	public boolean isLanguageObjC() { return getLanguage() == CXLanguage_ObjC; }
	public boolean isLanguageCPlusPlus() { return getLanguage() == CXLanguage_CPlusPlus; }


	/**
	 * @see clang.binding.ClangLibrary#clang_getCursorSemanticParent(clang.binding.ClangLibrary.CXCursor.ByValue)
	 */
	public Cursor getSemanticParent() {
		// get parent cxcursor
		CXCursor.ByValue pcx = clang_getCursorSemanticParent(cxcursor());
		if (pcx == null) return null;
		return new Cursor(pcx);
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_getCursorLexicalParent(clang.binding.ClangLibrary.CXCursor.ByValue)
	 */
	public Cursor getLexicalParent() {
		// get parent cxcursor
		CXCursor.ByValue pcx = clang_getCursorLexicalParent(cxcursor());
		if (pcx == null) return null;
		return new Cursor(pcx);
	}
	
	
	/**
	 * @see clang.binding.ClangLibrary#clang_getOverriddenCursors(clang.binding.ClangLibrary.CXCursor.ByValue, com.sun.jna.ptr.PointerByReference, com.sun.jna.ptr.IntByReference)
	 */
	@Deprecated
	void getOverriddenCursors(
			PointerByReference overridden, 
			IntByReference num_overridden) {
		clang_getOverriddenCursors(cxcursor(), overridden, num_overridden);
	}
//	/**
//	 * @see clang.ClangLibrary#clang_getOverriddenCursors(clang.ClangLibrary.CXCursor.ByValue, clang.ClangLibrary.CXCursor.ByReference[], java.nio.IntBuffer)
//	 */
//	public void getOverriddenCursors(ByReference[] overridden, IntBuffer num_overridden) {
//		clang_getOverriddenCursors(cxcursor(), overridden, num_overridden);
//	}
//	/**
//	 * @see clang.ClangLibrary#clang_getOverriddenCursors(clang.ClangLibrary.CXCursor.ByValue, clang.ClangLibrary.CXCursor.ByReference[], com.sun.jna.ptr.IntByReference)
//	 */
//	public void getOverriddenCursors(ByReference[] overridden, IntByReference num_overridden) {
//		clang_getOverriddenCursors(cxcursor(), overridden, num_overridden);
//	}
	/**
	 * @see clang.binding.ClangLibrary#clang_disposeOverriddenCursors(clang.binding.ClangLibrary.CXCursor)
	 */
	public void disposeOverriddenCursors(CXCursor overridden) {
		clang_disposeOverriddenCursors(cxcursor());
	}

	
	/**
	 * @see clang.binding.ClangLibrary#clang_getIncludedFile(clang.binding.ClangLibrary.CXCursor.ByValue)
	 */
	Pointer getIncludedFile() {
		return clang_getIncludedFile(cxcursor());
	}

	
	/**
	 * @see clang.binding.ClangLibrary#clang_getCursorLocation(clang.binding.ClangLibrary.CXCursor.ByValue)
	 */
	public SourceLocation getLocation() {
		return new SourceLocation(clang_getCursorLocation(cxcursor()));
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_getCursorExtent(clang.binding.ClangLibrary.CXCursor.ByValue)
	 */
	public SourceRange getExtent() {
		return new SourceRange(clang_getCursorExtent(cxcursor()));
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_getCursorType(clang.binding.ClangLibrary.CXCursor.ByValue)
	 */
	public Type getCursorType() {
		return new Type(clang_getCursorType(cxcursor()));
	}

	
	public abstract class Visitor implements CXCursorVisitor, CXChildVisitResult {
		@Override
		public int apply(CXCursor.ByValue cursor, CXCursor.ByValue parent, Pointer client_data) {
			return callback(cursor, parent, client_data);
		}
		public int callback(CXCursor.ByValue cursor, CXCursor.ByValue parent, Pointer client_data) {
			return apply(new Cursor(cursor), new Cursor(parent), client_data);
		}
		abstract public int apply(Cursor cursor, Cursor parent, Pointer client_data);
		
		public static final int Break = CXChildVisit_Break;
		public static final int Continue = CXChildVisit_Continue;
		public static final int Recurse = CXChildVisit_Recurse;
		
	}
	
	/**
	 * @see clang.binding.ClangLibrary#clang_visitChildren(clang.binding.ClangLibrary.CXCursor.ByValue, clang.binding.ClangLibrary.CXCursorVisitor, com.sun.jna.Pointer)
	 */
	public int visitChildren(Visitor visitor, Pointer client_data) {
		int rtn = clang_visitChildren(cxcursor(), visitor, client_data);
		return rtn;
	}
	
	
	/**
	 * @see clang.binding.ClangLibrary#clang_getCursorResultType(clang.binding.ClangLibrary.CXCursor.ByValue)
	 */
	public Type getResultType() {
		return new Type(clang_getCursorResultType(cxcursor));
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_isVirtualBase(clang.binding.ClangLibrary.CXCursor.ByValue)
	 */
	public boolean isVirtualBase() {
		return clang_isVirtualBase(cxcursor) != 0;
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_getCXXAccessSpecifier(clang.binding.ClangLibrary.CXCursor.ByValue)
	 */
	int getCXXAccessSpecifier() {
		return clang_getCXXAccessSpecifier(cxcursor);		
	}
	/// <i>native declaration : clang-c\Index.h:1374</i>
	public boolean isCxxInvalidAccessSpecifier() { return getCXXAccessSpecifier() == CX_CXXInvalidAccessSpecifier; }
	/// <i>native declaration : clang-c\Index.h:1375</i>
	public boolean isCxxPublic() { return getCXXAccessSpecifier() == CX_CXXPublic; }
	/// <i>native declaration : clang-c\Index.h:1376</i>
	public boolean isCxxProtected() { return getCXXAccessSpecifier() == CX_CXXProtected; }
	/// <i>native declaration : clang-c\Index.h:1377</i>
	public boolean isCxxPrivate() { return getCXXAccessSpecifier() == CX_CXXPrivate; }
	

	/**
	 * @see clang.binding.ClangLibrary#clang_getNumOverloadedDecls(clang.binding.ClangLibrary.CXCursor.ByValue)
	 */
	int getNumOverloadedDecls() {
		return clang_getNumOverloadedDecls(cxcursor);				
	}
	/**
	 * @see clang.binding.ClangLibrary#clang_getOverloadedDecl(clang.binding.ClangLibrary.CXCursor.ByValue, int)
	 */
	Cursor getOverloadedDecl(int index) {
		return new Cursor(clang_getOverloadedDecl(cxcursor, index));
	}
	public Cursor[] getOverloadedDecls() {
		int N = getNumOverloadedDecls();
		Cursor[] decls = new Cursor[N];
		for (int i=0; i<N; i++) {
			decls[i] = getOverloadedDecl(i);
		}
		return decls;
	}

	
	/**
	 * @see clang.binding.ClangLibrary#clang_getIBOutletCollectionType(clang.binding.ClangLibrary.CXCursor.ByValue)
	 */
	public Type getIBOutletCollectionType() {
		return new Type(clang_getIBOutletCollectionType(cxcursor));
	}


	/**
	 * @see clang.binding.ClangLibrary#clang_getCursorUSR(clang.binding.ClangLibrary.CXCursor.ByValue)
	 */
	public String getUSR() {
		String str = clang_getCursorUSR(cxcursor);
		return str;
	}
	
	
	/**
	 * @see clang.binding.ClangLibrary#clang_getCursorSpelling(clang.binding.ClangLibrary.CXCursor.ByValue)
	 */
	public String getSpelling() {
		return clang_getCursorSpelling(cxcursor);
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_getCursorDisplayName(clang.binding.ClangLibrary.CXCursor.ByValue)
	 */
	public String getDisplayName() {
		String str = clang_getCursorDisplayName(cxcursor);
		return str;
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_getCursorReferenced(clang.binding.ClangLibrary.CXCursor.ByValue)
	 */
	public Cursor getReferenced() {
		return new Cursor(clang_getCursorReferenced(cxcursor));
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_getCursorDefinition(clang.binding.ClangLibrary.CXCursor.ByValue)
	 */
	public Cursor getDefinition() {
		return new Cursor(clang_getCursorDefinition(cxcursor));
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_isCursorDefinition(clang.binding.ClangLibrary.CXCursor.ByValue)
	 */
	public boolean isDefinition() {
		return clang_isCursorDefinition(cxcursor) != 0;
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_getCanonicalCursor(clang.binding.ClangLibrary.CXCursor.ByValue)
	 */
	public Cursor getCanonicalCursor() {
		return new Cursor(clang_getCanonicalCursor(cxcursor));
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_CXXMethod_isStatic(clang.binding.ClangLibrary.CXCursor.ByValue)
	 */
	public boolean CXXMethod_isStatic() {
		return clang_CXXMethod_isStatic(cxcursor) != 0;
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_getTemplateCursorKind(clang.binding.ClangLibrary.CXCursor.ByValue)
	 */
	int getTemplateCursorKind() {
		int kind = clang_getTemplateCursorKind(cxcursor);
		// TODO enumerate
		return kind;
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_getCursorKindSpelling(int)
	 */
	String getCursorKindSpelling(int kind) {
		String str = clang_getCursorKindSpelling(kind);
		return str;
	}
	public String getCursorKindSpelling() { return getCursorKindSpelling(cxcursor.kind); }

	/**
	 * @see clang.binding.ClangLibrary#clang_getDefinitionSpellingAndExtent(clang.binding.ClangLibrary.CXCursor.ByValue, com.sun.jna.ptr.PointerByReference, com.sun.jna.ptr.PointerByReference, com.sun.jna.ptr.IntByReference, com.sun.jna.ptr.IntByReference, com.sun.jna.ptr.IntByReference, com.sun.jna.ptr.IntByReference)
	 */
	@Deprecated
	public void getDefinitionSpellingAndExtent(
			PointerByReference startBuf, PointerByReference endBuf,
			IntByReference startLine, IntByReference startColumn,
			IntByReference endLine, IntByReference endColumn) {
		clang_getDefinitionSpellingAndExtent(
				cxcursor, startBuf, endBuf, startLine, startColumn, endLine, endColumn);
	}

//	/**
//	 * @see clang.ClangLibrary#clang_getDefinitionSpellingAndExtent(clang.ClangLibrary.CXCursor.ByValue, java.lang.String[], java.lang.String[], java.nio.IntBuffer, java.nio.IntBuffer, java.nio.IntBuffer, java.nio.IntBuffer)
//	 */
//	public void getDefinitionSpellingAndExtent(String[] startBuf,
//			String[] endBuf, IntBuffer startLine, IntBuffer startColumn,
//			IntBuffer endLine, IntBuffer endColumn) {
//		ClangLibrary.clang_getDefinitionSpellingAndExtent(
//				cxcursor, startBuf, endBuf, startLine, startColumn, endLine, endColumn);
//	}

	
	/**
	 * @see clang.binding.ClangLibrary#clang_getSpecializedCursorTemplate(clang.binding.ClangLibrary.CXCursor.ByValue)
	 */
	public Cursor getSpecializedCursorTemplate() {
		return new Cursor(ClangLibrary.clang_getSpecializedCursorTemplate(cxcursor));
	}
	
	
	
	///////////////////////////////////////////////////////////////////
	// ObjC
	
	/**
	 * @see clang.binding.ClangLibrary#clang_getDeclObjCTypeEncoding(clang.binding.ClangLibrary.CXCursor.ByValue)
	 */
	public String getDeclObjCTypeEncoding() {
		String str = ClangLibrary.clang_getDeclObjCTypeEncoding(cxcursor());
		return str;
	}

	
}
