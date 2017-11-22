import kotlinx.cinterop.*
import platform.posix.*

/**
 * The starting point
 */
fun main(args: Array<String>) {

	// Get command line input
    if (args.size != 2) {
        println("Usage: csv_filename column_number")
        return
    }
    val fileName = args[0]
    val columnNumber = args[1].toInt()

	// Open the file
    val file = fopen(fileName, "r")
    if (file == null) {
        perror("Failed to open the file named $fileName")
        return
    }

    val result = mutableMapOf<String, Int>()

    try {
        memScoped {
			// Memory allocation for buffer
            val bufferLength = 64 * 1024
            val buffer = allocArray<ByteVar>(bufferLength)
			
			while (true) {
				// Get line
				val nextLine = fgets(buffer, bufferLength, file)?.toKString() ?: break
				
				// Split line
                val records = nextLine.split(",")
                val key = records[columnNumber]
				
				// Adjust the occurance count
                val current = result[key] ?: 0
                result[key] = current + 1
            }
        }
    } finally {
        fclose(file)
    }

	// Prints the result
    result.forEach {
        println("${it.key} -> ${it.value}")
    }
}