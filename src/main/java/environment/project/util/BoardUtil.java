package environment.project.util;

import environment.project.exception.ResourceNotFoundException;

public class BoardUtil {
    /**
     * 메서드: String -> Long 타입 변환 기능 구현
     *
     * @author 이권석
     */
    public static Long parseboardNo(String boardNo) {
        try {
            return Long.parseLong(boardNo);
        } catch (NumberFormatException e) {
            throw new ResourceNotFoundException("Board id must be a numeric string: " + boardNo);
        }

    }
}
