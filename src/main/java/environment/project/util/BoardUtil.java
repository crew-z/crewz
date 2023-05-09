package environment.project.util;

import environment.project.exception.ResourceNotFoundException;

public class BoardUtil {

    // 기능: String -> Long 타입 변환
    public static Long parseStringtoLong(String boardNo) {
        try {
            return Long.parseLong(boardNo);
        } catch (NumberFormatException e) {
            throw new ResourceNotFoundException("Board id must be a numeric string: " + boardNo);
        }
    }
}
