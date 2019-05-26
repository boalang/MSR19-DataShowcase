import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Writable;
import com.google.protobuf.CodedInputStream;

public class ReadDataset {
	
	// Set this to location of the directory dataset_directory/ast/data
		private static final String astpath = "/Users/sumon/python_updated/ast/data";

		public static void main(String[] args) throws IOException {
			Configuration conf = new Configuration();
			FileSystem fileSystem = FileSystem.get(conf);
		
			Writable key = new LongWritable();
			BytesWritable val = new BytesWritable();
			SequenceFile.Reader r = new SequenceFile.Reader(fileSystem, new Path(astpath), conf);
			
			while (r.next(key, val)) {
				System.out.println("--- NEXT PROJECT ---");
				byte[] bytes = val.getBytes();
				boa.types.Ast.ASTRoot ast = boa.types.Ast.ASTRoot.parseFrom((CodedInputStream.newInstance(bytes, 0, val.getLength())));
				System.out.println(ast);
				break; // remove this line to print AST of all the files 
			}
			r.close();
		}

}
