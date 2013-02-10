package clang;

import clang.binding.ClangLibrary;

public class Type implements ClangLibrary.CXTypeKind {

	private ClangLibrary.CXType.ByValue cxtype;
	ClangLibrary.CXType.ByValue cxtype() { return cxtype; }
	
	Type(ClangLibrary.CXType.ByValue ty) {
		this.cxtype = ty;
	}
	
	
	@Override
	public String toString() {
		return "Type [" + getTypeKindSpelling() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cxtype == null) ? 0 : cxtype.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof Type)) return false;
		Type other = (Type) obj;
		if (cxtype == null) {
			if (other.cxtype != null)
				return false;
		} else if (!equalTypes(cxtype, other.cxtype))
			return false;
		return true;
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_equalTypes(clang.binding.ClangLibrary.CXType.ByValue, clang.binding.ClangLibrary.CXType.ByValue)
	 */
	boolean equalTypes(clang.binding.ClangLibrary.CXType.ByValue A,
			clang.binding.ClangLibrary.CXType.ByValue B) {
		return ClangLibrary.clang_equalTypes(A, B) != 0;
	}

	
	/**
	 * @see clang.binding.ClangLibrary#clang_getCanonicalType(clang.binding.ClangLibrary.CXType.ByValue)
	 */
	public Type getCanonicalType() {
		return new Type(ClangLibrary.clang_getCanonicalType(cxtype));
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_isConstQualifiedType(clang.binding.ClangLibrary.CXType.ByValue)
	 */
	public boolean isConstQualifiedType() {
		return ClangLibrary.clang_isConstQualifiedType(cxtype) != 0;
	}
	public boolean isConst() { return isConstQualifiedType(); }
	
	/**
	 * @see clang.binding.ClangLibrary#clang_isVolatileQualifiedType(clang.binding.ClangLibrary.CXType.ByValue)
	 */
	public boolean isVolatileQualifiedType() {
		return ClangLibrary.clang_isVolatileQualifiedType(cxtype) != 0;
	}
	public boolean isVolatile() { return isVolatileQualifiedType(); }
	
	/**
	 * @see clang.binding.ClangLibrary#clang_isRestrictQualifiedType(clang.binding.ClangLibrary.CXType.ByValue)
	 */
	public boolean isRestrictQualifiedType() {
		return ClangLibrary.clang_isRestrictQualifiedType(cxtype) != 0;
	}
	public boolean isRestrict() { return isRestrictQualifiedType(); }
	
	/**
	 * @see clang.binding.ClangLibrary#clang_getPointeeType(clang.binding.ClangLibrary.CXType.ByValue)
	 */
	public Type getPointeeType() {
		return new Type(ClangLibrary.clang_getPointeeType(cxtype));
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_getTypeDeclaration(clang.binding.ClangLibrary.CXType.ByValue)
	 */
	public Cursor getTypeDeclaration() {
		return new Cursor(ClangLibrary.clang_getTypeDeclaration(cxtype));
	}


	/**
	 * @see clang.binding.ClangLibrary#clang_getTypeKindSpelling(int)
	 */
	public String getTypeKindSpelling() {
		String str = ClangLibrary.clang_getTypeKindSpelling(cxtype().kind);
		return str;
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_getResultType(clang.binding.ClangLibrary.CXType.ByValue)
	 */
	public Type getResultType() {
		return new Type(ClangLibrary.clang_getResultType(cxtype));
	}

	/**
	 * @see clang.binding.ClangLibrary#clang_isPODType(clang.binding.ClangLibrary.CXType.ByValue)
	 */
	public boolean isPODType() {
		return ClangLibrary.clang_isPODType(cxtype) != 0;
	}

}
