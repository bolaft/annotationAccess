/**
 * 
 */
package corr.annotationAccess.annotators;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import annotationAccess.util.MiscUtil;

/**
 * Annotator which iterates over the whole annotation index and 
 * echoes both the type and the covered text of each annotation 
 */
public class AnnotationIndexEchoAnnotator extends JCasAnnotator_ImplBase {
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		AnnotationIndex<Annotation>	anAnnotationIndex = aJCas.getAnnotationIndex();
		// selectAll(jcas) of uimaFIT get all the feature structures (inheriting from TOP), it is so required to filter them

		for (Annotation annotation : anAnnotationIndex) {
			MiscUtil.echo(annotation);
		}
	}
}
